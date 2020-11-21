package com.example.digitalturbine.di

import com.example.digitalturbine.data.model.api.ApiService
import com.example.digitalturbine.data.model.db.OkHttpClientHolder
import com.example.digitalturbine.data.model.db.OkHttpInterceptorsHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpInterceptorsHolder(okHttpInterceptorsHolder: OkHttpInterceptorsHolder): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClientHolder.instance.newClientBuilder()
        builder.addInterceptor(okHttpInterceptorsHolder.getLoggingInterceptor())
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://ads.appia.com")
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}