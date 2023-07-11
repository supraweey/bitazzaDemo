package com.example.bitazzademo.service

import android.content.Context
import android.content.SharedPreferences
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BitazzaHttpClient {
    companion object {
        val BASE_URL = "https://apexapi.bitazza.com:8443/"
    }

    fun createRetrofit(context: Context, pref: PreferenceStoragable): Retrofit {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder().baseUrl(BASE_URL)
            .client(createOkHttpClient(context, pref))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun createOkHttpClient(
        context: Context,
        pref: PreferenceStoragable,
        interceptors: List<Interceptor> = listOf()
    ): OkHttpClient {
        val bodyLogging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        interceptors.forEach { client.addInterceptor(it) }

        if (BuildConfig.DEBUG) {
            client.addInterceptor(BasicAuthInterceptor(pref.getString(USER_KEY_TOKEN, "") ?: ""))
            client.addNetworkInterceptor(bodyLogging)
            client.addInterceptor(ChuckInterceptor(context))
        }
        return client.build()
    }

}