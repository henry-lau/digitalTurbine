package com.example.digitalturbine.utilities

import android.Manifest.permission
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import androidx.core.content.ContextCompat

/**
 * Utils class to get information about network
 */
object NetworkUtil {
    private const val CONNECTION_TYPE_WIFI = "wifi"
    private const val CONNECTION_TYPE_ETHERNET = "ethernet"
    private const val CONNECTION_TYPE_NONE = "none"
    private const val CONNECTION_TYPE_UNKNOWN = "unknown"

    /**
     * Returns the connection type as follows:
     *
     *
     * 1. "wifi" if on wifi
     * 2. "ProviderName|ConnectionStandard|MCC|MNC" if on cellular
     * 3. "ethernet" if connected via ethernet
     * 4. "none" if no connection
     * 5. "unknown" for all other cases
     */
    fun getConnectionType(context: Context): String {

        // Calling getActiveNetworkInfo without this permission results in a crash.
        if (ContextCompat.checkSelfPermission(
                context,
                permission.ACCESS_NETWORK_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return CONNECTION_TYPE_UNKNOWN
        }
        val manager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
            ?: return CONNECTION_TYPE_NONE
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkInfo = manager.activeNetwork ?: return CONNECTION_TYPE_NONE
            val capabilities = manager.getNetworkCapabilities(networkInfo)
                ?: return CONNECTION_TYPE_NONE
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                CONNECTION_TYPE_WIFI
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                getCellularConnectionString(context)
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                CONNECTION_TYPE_ETHERNET
            } else {
                CONNECTION_TYPE_UNKNOWN
            }
        } else {
            val networkInfo = manager.activeNetworkInfo
            if (networkInfo == null || !networkInfo.isConnected) {
                return CONNECTION_TYPE_NONE
            }
            if (networkInfo.type == ConnectivityManager.TYPE_WIFI) {
                CONNECTION_TYPE_WIFI
            } else if (networkInfo.type == ConnectivityManager.TYPE_MOBILE) {
                getCellularConnectionString(context)
            } else if (networkInfo.type == ConnectivityManager.TYPE_ETHERNET) {
                CONNECTION_TYPE_ETHERNET
            } else {
                CONNECTION_TYPE_UNKNOWN
            }
        }
    }

    /**
     * Returns true if we are connected to the internet. Also, if we do not have the permission to check: we assume
     * we are connected.
     */
    fun haveInternet(context: Context?): Boolean {
        return if (context == null) {
            false
        } else getConnectionType(context) != CONNECTION_TYPE_NONE
    }

    private fun getCellularConnectionString(context: Context): String {
        val tManager = context.getSystemService(
            Context.TELEPHONY_SERVICE
        ) as TelephonyManager
            ?: return ""
        val connectionString = StringBuilder()
        connectionString.append(tManager.networkOperatorName)
        connectionString.append("|unknown|")
        val operator = tManager.networkOperator
        if (!operator.isNullOrEmpty()) {
            connectionString.append(operator.substring(0, 3))
            connectionString.append("|")
            connectionString.append(operator.substring(3))
        } else {
            connectionString.append("unknown|unknown")
        }
        return connectionString.toString()
    }
}
