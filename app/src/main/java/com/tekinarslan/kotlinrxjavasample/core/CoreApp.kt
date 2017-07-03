package com.tekinarslan.kotlinrxjavasample.core

import android.app.Application
import com.tekinarslan.kotlinrxjavasample.network.NetworkManager


/**
 * Created by selimtekinarslan on 6/29/2017.
 */
class CoreApp : Application() {

    companion object {
        var instance: CoreApp? = null
    }

    private var networkManager: NetworkManager? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun getNetworkManager(): NetworkManager {
        if (networkManager == null) {
            networkManager = NetworkManager()
        }
        return networkManager as NetworkManager
    }

}