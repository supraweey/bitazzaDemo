package com.example.bitazzademo.data

import retrofit2.http.GET

interface AccountService {
    @GET("AP/Authenticate")
    suspend fun authentication(): AuthenticationResponse
//    suspend fun authentication(@Query("api_key") apiKey: String, @Query("page") page: Int): MovieResponse
}