package com.test.tribal.rest.interceptors

import android.util.Log
import com.test.tribal.App
import com.test.tribal.rest.exceptions.NoNetworkAvalaibleException
import com.test.tribal.ui.tools.NetworkChecker
import okhttp3.Interceptor
import okhttp3.Response

class NetworkAvailableInterceptor: Interceptor {

    private val TAG = "NetworkInterceptor"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        if (!NetworkChecker.isConnected()) {
            Log.d(TAG, "Device is not connect to any network")
            App.instance.setIsConnected(false)
            throw NoNetworkAvalaibleException("No connected to Internet")
        } else
            App.instance.setIsConnected(true)
        return chain.proceed(request)
    }
}