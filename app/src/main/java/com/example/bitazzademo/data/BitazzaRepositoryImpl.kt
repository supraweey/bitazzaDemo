package com.example.bitazzademo.data

import com.example.bitazzademo.domain.AuthenticationItem
import com.example.bitazzademo.domain.BitazzaRepository
import com.example.bitazzademo.domain.ProductItem
import com.example.bitazzademo.network.Networkable
import kotlinx.coroutines.flow.Flow

class BitazzaRepositoryImpl(
    private val networkable: Networkable,
    private val service: BitazzaService
) : BitazzaRepository {
    override fun authentication(request: String): Flow<AuthenticationItem> =
        object : BaseService<AuthenticationResponse, AuthenticationItem>(networkable) {
            override suspend fun callApi(): AuthenticationResponse = service.authentication(request)

            override fun mapper(from: AuthenticationResponse): AuthenticationItem =
                from.mapToDomain()

        }.execute()

    override fun getProduct(request: Int): Flow<List<ProductItem>> =
        object : BaseService<List<GetProductResponse>, List<ProductItem>>(networkable) {
            override suspend fun callApi(): List<GetProductResponse> = service.getProduct(request)

            override fun mapper(from: List<GetProductResponse>): List<ProductItem> =
                from.map { it.mapToDomain() }

        }.execute()
}