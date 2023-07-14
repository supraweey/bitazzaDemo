package com.example.bitazzademo.network

import android.content.Context
import android.net.ConnectivityManager

class NetworkManager private constructor(networkable: Networkable): Networkable by networkable {
    companion object{
        fun init(context: Context): NetworkManager {
            return NetworkManager(NetworkUtils(context.getSystemService(ConnectivityManager::class.java)))
        }
    }
}