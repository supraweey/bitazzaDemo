package com.example.bitazzademo.domain

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {
    fun authentication(request: String): Flow<AuthenticationItem>
}