package com.example.bitazzademo.encryption

interface AppEncryptable {
    fun encryptAES(plainData: String, encryptionKey: String): String

    fun decryptAES(encryptData: String, encryptionKey: String): String
}