package com.example.digitalturbine.data.model.db

import okhttp3.OkHttpClient

class OkHttpClientHolder private constructor(
    private val defaultClient: OkHttpClient = OkHttpClient()
    /**
     * Always use shared instance
     * https://square.github.io/okhttp/3.x/okhttp/okhttp3/OkHttpClient.html
     */
) {

    fun newClientBuilder(): OkHttpClient.Builder = defaultClient.newBuilder()

    companion object {
        @JvmStatic
        val instance: OkHttpClientHolder = OkHttpClientHolder()
    }
}