package com.example.bitazzademo.data

import com.example.bitazzademo.domain.AuthenticationRepository
import com.example.bitazzademo.domain.AuthenticationItem
import com.example.bitazzademo.network.Networkable
import kotlinx.coroutines.flow.Flow

class AuthenticationRepositoryImpl(
    private val networkable: Networkable,
    private val service: AuthenticationService
) : AuthenticationRepository {
    override fun authentication(request: String): Flow<AuthenticationItem> =
        object : BaseService<AuthenticationResponse, AuthenticationItem>(networkable) {
            override suspend fun callApi(): AuthenticationResponse = service.authentication(request)

            override fun mapper(from: AuthenticationResponse): AuthenticationItem = from.mapToDomain()

        }.execute()
}