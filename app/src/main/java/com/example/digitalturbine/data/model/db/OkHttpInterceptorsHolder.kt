package com.example.digitalturbine.data.model.db

import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Inject

/**
 * Holder for Interceptors so that would be used seamlessly across the whole application
 */
class OkHttpInterceptorsHolder @Inject constructor() {

    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
