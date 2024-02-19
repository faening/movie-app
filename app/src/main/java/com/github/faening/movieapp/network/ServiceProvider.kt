package com.github.faening.movieapp.network

import com.github.faening.movieapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceProvider {
    companion object {
        private const val DEFAULT_TIMEOUT = 30L
    }

    private val client = OkHttpClient
        .Builder()
        .connectTimeout(Companion.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(Companion.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit =  Retrofit
        .Builder()
        .baseUrl(BuildConfig.TMDB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun <API> createService(apiClass: Class<API>): API = retrofit.create(apiClass)
}