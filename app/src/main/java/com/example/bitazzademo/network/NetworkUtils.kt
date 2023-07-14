package com.example.bitazzademo.network

import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtils(private val connectivityManager: ConnectivityManager) : Networkable {
    override fun isInternetConnection(): Boolean {
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_VPN)
    }
}