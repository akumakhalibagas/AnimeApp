package com.makhalibagas.core.di

import com.makhalibagas.core.data.source.remote.network.KitsuService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val hostname = "kitsu.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/IzqLYRUtgFqJhoPEvqPDq0t0JfcRh+3Gt/BxsWZIR+Q=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(125, TimeUnit.SECONDS)
            .readTimeout(125, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }

    @Provides
    fun provideKitsuService(okHttpClient: OkHttpClient): KitsuService {
        return Retrofit.Builder()
            .baseUrl("https://kitsu.io")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(KitsuService::class.java)
    }
}

