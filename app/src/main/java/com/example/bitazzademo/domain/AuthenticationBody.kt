package com.example.bitazzademo.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AuthenticationBody(
    val token: String?
) : Parcelable
