package com.example.bitazzademo.domain.manager

import javax.crypto.SecretKey

interface AppKeyable {
    val aesKey: SecretKey?
}