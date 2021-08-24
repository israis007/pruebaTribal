package com.test.tribal.rest.interceptors

import com.test.tribal.BuildConfig
import okhttp3.Interceptor

private const val ACCEPT        = "Accept"
private const val PAGE_LIMIT    = "X-Per-Page"
private const val MAX_RESULTS   = "X-Total"
private const val JSON_ACCEPT   = "application/json"

class UnsplashHeaders {
    companion object {
        fun createSplashHeaders() : Interceptor {
            return Interceptor { chain ->
                val reqBuilder = chain.request().newBuilder()

                reqBuilder.addHeader(ACCEPT,        JSON_ACCEPT)
                reqBuilder.addHeader(PAGE_LIMIT,    BuildConfig.PAGE_LIMIT.toString())
                reqBuilder.addHeader(MAX_RESULTS,   BuildConfig.MAX_RESULTS.toString())

                chain.proceed(reqBuilder.build())
            }
        }
    }
}