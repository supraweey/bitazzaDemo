package com.example.bitazzademo.domain

import kotlinx.coroutines.flow.Flow

class GetProductListUseCase(private val repository: BitazzaRepository) :
    UseCase<Int, List<ProductItem>>() {
    override fun validateRequest(request: Int): Int = request

    override suspend fun executeRepo(
        request: Int,
        isRetry: Boolean
    ): Flow<List<ProductItem>> =
        repository.getProduct(request)
}