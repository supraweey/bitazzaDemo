package com.example.bitazzademo.domain

import kotlinx.coroutines.flow.Flow

class LoginUseCase(private val repository: BitazzaRepository) :
    UseCase<String, AuthenticationItem>() {
    override fun validateRequest(request: String): String = request

    override suspend fun executeRepo(request: String, isRetry: Boolean): Flow<AuthenticationItem> =
        repository.authentication(request)
}