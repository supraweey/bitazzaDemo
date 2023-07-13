package com.example.bitazzademo.encryption

import android.os.Build
import org.bouncycastle.util.encoders.Base64
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


class AppEncrypter: AppEncryptable {
    override fun encryptAES(plainData: String, encryptionKey: SecretKey?): String {
        val cipher = getAESCipher(Cipher.ENCRYPT_MODE, encryptionKey)
        val encryptedBytes = cipher.doFinal(plainData.toByteArray())
        val encryptedString = String(Base64.encode(encryptedBytes))
        return encryptedString.replace("(\\r|\\n|\\r\\n)+", "")
    }

    override fun decryptAES(encryptData: String, encryptionKey: SecretKey?): String {
        return try {
            val decodeData = Base64.decode(encryptData)
            val cipher = getAESCipher(Cipher.DECRYPT_MODE, encryptionKey)
            val plainDataBytes = cipher.doFinal(decodeData)
            String(plainDataBytes)
        } catch (e: Exception) {
            encryptData
        }
    }

    private fun getAESCipher(operationMode: Int, encryptionKey: SecretKey?): Cipher {
        val secretKey = SecretKeySpec(encryptionKey?.encoded, "AES")
        val cipher = when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.P -> Cipher.getInstance("AES/ECB/PKCS5Padding")
            else -> Cipher.getInstance("AES/ECB/PKCS7Padding", "BC")
        }

        cipher.init(operationMode, secretKey)
        return cipher
    }

}