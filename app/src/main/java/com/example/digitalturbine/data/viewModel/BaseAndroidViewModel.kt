package com.example.digitalturbine.data.viewModel

import androidx.lifecycle.ViewModel
import com.example.digitalturbine.utilities.SingleLiveEvent

/**
 * This is base class for our AndroidViewModels.
 * Put here ONLY logic that will be used by ALL classes that inherit it.
 * For one off, use composition pattern or leave it up there.
 */
abstract class BaseAndroidViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val _onLoading = SingleLiveEvent<Boolean>()
    val onLoading: SingleLiveEvent<Boolean> = _onLoading
}