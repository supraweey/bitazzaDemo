package com.example.bitazzademo.domain.manager

import javax.crypto.KeyGenerator
import javax.crypto.SecretKey


class AppKeyer : AppKeyable {
    override val aesKey: SecretKey?
        get() = getKey()

    private fun getKey(): SecretKey? {
        var secretKey: SecretKey? = null
        try {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256)
            secretKey = keyGenerator.generateKey()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return secretKey
    }

}