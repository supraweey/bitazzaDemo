package com.example.bitazzademo.data

import com.example.bitazzademo.domain.AuthenticationItem
import com.google.gson.annotations.SerializedName

data class AuthenticationResponse(
    @SerializedName("Authenticated") val authenticated: Boolean?,
    @SerializedName("UserId") val userId: Int?,
    @SerializedName("Token") val token: String?,
    @SerializedName("AccountId") val accountId: Int?,
    @SerializedName("OMSId") val oMSId: Int?
)

fun AuthenticationResponse.mapToDomain(): AuthenticationItem =
    AuthenticationItem(
        authenticated = authenticated,
        userId = userId,
        token = token,
        accountId = accountId,
        oMSId = oMSId
    )