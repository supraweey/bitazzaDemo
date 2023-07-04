package com.example.bitazzademo.data

import com.example.bitazzademo.domain.AccountRepository
import com.example.bitazzademo.domain.AuthenticationItem
import com.example.bitazzademo.network.Networkable
import kotlinx.coroutines.flow.Flow

class AccountRepositoryImpl(
    private val networkable: Networkable,
    private val service: AccountService
) : AccountRepository {
    override fun authentication(): Flow<AuthenticationItem> =
        object : BaseService<AuthenticationResponse, AuthenticationItem>(networkable) {
            override suspend fun callApi(): AuthenticationResponse = service.authentication()

            override fun mapper(from: AuthenticationResponse): AuthenticationItem = from.mapToDomain()

        }.execute()
}