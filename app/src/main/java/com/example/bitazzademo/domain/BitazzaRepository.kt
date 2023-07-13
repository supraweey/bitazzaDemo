package com.example.bitazzademo.domain

import kotlinx.coroutines.flow.Flow

interface BitazzaRepository {
    fun authentication(request: String): Flow<AuthenticationItem>

    fun getProduct(omsId: Int): Flow<List<ProductItem>>
}