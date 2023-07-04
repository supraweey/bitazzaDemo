package com.example.bitazzademo.network

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import androidx.annotation.RequiresApi
import com.android.supraweey.tmdbclient.network.NetworkUtils

class NetworkManager private constructor(networkable: Networkable): Networkable by networkable {
    companion object{
        fun init(context: Context): NetworkManager {
            return NetworkManager(NetworkUtils(context.getSystemService(ConnectivityManager::class.java)))
        }
    }
}