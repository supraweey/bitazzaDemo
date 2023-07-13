package com.example.bitazzademo.data

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface BitazzaService {
    @GET("AP/Authenticate")
    suspend fun authentication(@Header("Authorization") token: String): AuthenticationResponse

    @GET("AP/GetProducts")
    suspend fun getProduct(@Query("OMSId") omsId: Int): List<GetProductResponse>
}