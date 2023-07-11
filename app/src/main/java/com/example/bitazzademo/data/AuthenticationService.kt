package com.example.bitazzademo.data

import retrofit2.http.GET
import retrofit2.http.Header

interface AuthenticationService {
    @GET("AP/Authenticate")
    suspend fun authentication(@Header("Authorization") token: String): AuthenticationResponse
}