package com.example.digitalturbine.ui.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.digitalturbine.data.viewModel.ViewModelFactory

/**
 * This is base Fragment class for Fragments that are using Android ViewModel as interaction
 */
open class BaseViewModelFragment : Fragment() {

    protected fun <T> LiveData<T>.observeMe(observer: (T) -> Unit) {
        observe(viewLifecycleOwner, Observer { observer(it as T) })
    }

    protected fun <T> LiveData<T?>.observeNotNull(observer: (T) -> Unit) {
        observeNotNull(this@BaseViewModelFragment, observer)
    }
}

fun <T> LiveData<T?>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (T) -> Unit) {
    observe(lifecycleOwner, Observer {
        if (it != null) observer(it)
    })
}

fun Fragment.getViewModelFactory(): ViewModelFactory {
    return ViewModelFactory(this.requireContext())
}