package com.example.digitalturbine.data.viewModel

import android.util.Log
import androidx.annotation.UiThread
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.digitalturbine.data.model.db.data.ads
import kotlinx.coroutines.launch

/**
 *   Use ViewModelProvider and scope ViewModel to the Activity/Fragment and provide an Observable
 *   for ApiService status
 */
open class AdsViewModel @ViewModelInject constructor (
    private val adsRepository: Repository
) : BaseAndroidViewModel() {

    private val _adList by lazy {
        MutableLiveData<ads>().also {
            getAdList()
        }
    }
    val adList: LiveData<ads?> = _adList

    @UiThread
    fun getAdList() {
        viewModelScope.launch {
            runCatching {
                onLoadStarted()
                adsRepository.fetchList(MY_LAST_NAME)
            }.onSuccess {
                onLoadFinished()
                _adList.value = it
            }.onFailure { throwable ->
                Log.e(LOG_TAG, "Error occurred while getting Ads", throwable)
                onLoadFinished()
            }
        }
    }

    private fun onLoadStarted() {
        _onLoading.value = true
    }

    private fun onLoadFinished() {
        _onLoading.value = false
    }

    companion object {
        private val LOG_TAG = AdsViewModel::class.java.simpleName
        const val MY_LAST_NAME: String = "lau"
    }
}
