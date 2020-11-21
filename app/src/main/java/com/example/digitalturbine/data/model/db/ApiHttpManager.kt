package com.example.digitalturbine.data.model.db

import android.content.Context
import com.example.digitalturbine.data.model.api.ApiService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.components.ApplicationComponent

/**
 * Constructs for ApiService instance using retrofit
 */
object ApiHttpManager {

    @InstallIn(ApplicationComponent::class)
    @EntryPoint
    interface ApiProviderEntryPoint {
        fun getRetrofit(): ApiService
    }

    /**
     * Gets a retrofit instance provided by Hilt using the @EntryPoint annotated interface.
     */
    private fun getApiService(appContext: Context): ApiService {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            ApiProviderEntryPoint::class.java
        )
        return hiltEntryPoint.getRetrofit()
    }

    fun getService(context: Context): ApiService {
        return getApiService(context)
    }
}
