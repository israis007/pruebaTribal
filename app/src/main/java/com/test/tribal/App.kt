package com.test.tribal

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class App: MultiDexApplication() {

    private var isConnected = false

    companion object {
        lateinit var instance: App
            private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
    }

    fun setIsConnected(isConnected : Boolean){
        this@App.isConnected = isConnected
    }

    fun isConnected() = this@App.isConnected

}