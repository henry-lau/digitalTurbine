package com.example.digitalturbine.data.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.digitalturbine.data.model.db.ApiHttpManager
import kotlinx.coroutines.Dispatchers

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val ioThread = Dispatchers.IO

        when {
            modelClass.isAssignableFrom(AdsViewModel::class.java) -> {
                return AdsViewModel(AdsRepository(ApiHttpManager.getService(context), ioThread)) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
