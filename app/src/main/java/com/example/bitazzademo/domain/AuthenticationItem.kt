package com.example.bitazzademo.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthenticationItem(
    val authenticated: Boolean?,
    val userId: Int?,
    val token: String?,
    val accountId: Int?,
    val oMSId: Int?,
) : Parcelable
