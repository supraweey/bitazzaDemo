package com.example.bitazzademo.service

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class BasicAuthInterceptor(token: String) : Interceptor {
    private val authToken: String = token

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val authenticatedRequest: Request = request.newBuilder()
            .header("Authorization", authToken).build()
        return chain.proceed(authenticatedRequest)
    }
}