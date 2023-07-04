package com.example.bitazzademo.domain

import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    fun authentication(): Flow<AuthenticationItem>
}