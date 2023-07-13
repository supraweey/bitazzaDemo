package com.example.bitazzademo.encryption

import javax.crypto.SecretKey

interface AppEncryptable {
    fun encryptAES(plainData: String, encryptionKey: SecretKey?): String

    fun decryptAES(encryptData: String, encryptionKey: SecretKey?): String
}