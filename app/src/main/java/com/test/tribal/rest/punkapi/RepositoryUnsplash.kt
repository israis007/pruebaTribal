package com.test.tribal.rest.punkapi

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.test.tribal.BuildConfig
import com.test.tribal.rest.interceptors.NetworkAvailableInterceptor
import com.test.tribal.rest.interceptors.UnsplashHeaders
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RepositoryUnsplash {


    companion object {

        private val TAG = this::class.java.simpleName

        private val gsonConverterFactory by lazy {
            GsonConverterFactory.create(Gson().newBuilder().serializeNulls().setDateFormat(BuildConfig.Date_Pattern).create())
        }

        private val logInterceptor: Interceptor by lazy {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        private fun createClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(logInterceptor)
            builder.addInterceptor(UnsplashHeaders.createSplashHeaders())
            builder.addInterceptor(NetworkAvailableInterceptor())
            builder.callTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
            builder.readTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
            builder.connectTimeout(BuildConfig.TIME_OUT, TimeUnit.SECONDS)
            return builder.build()
        }

        fun getClient(): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.Unsplash_URL)
                .client(createClient())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(gsonConverterFactory)
                .build()

        fun clientAPI(): InterfaceUnsplash =
            getClient()
                .create(InterfaceUnsplash::class.java)

    }
}