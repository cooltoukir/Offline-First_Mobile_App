package com.toukirahmed.offline_firstmobileapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class NetworkObserver(context: Context) {

    var isConnected by mutableStateOf(true)
        private set

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val callback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            isConnected = true
        }

        override fun onLost(network: Network) {
            isConnected = false
        }
    }

    fun startObserving() {
        connectivityManager.registerDefaultNetworkCallback(callback)
    }

    fun stopObserving() {
        connectivityManager.unregisterNetworkCallback(callback)
    }
}