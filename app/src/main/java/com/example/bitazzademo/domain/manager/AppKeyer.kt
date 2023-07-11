package com.example.bitazzademo.domain.manager

import android.content.Context

class AppKeyer constructor(private val context: Context): AppKeyable {

    private var getJniKeyCounter = 0
    private external fun getJNIKey(type: Int): String

    override val aesKey: String
        get() = getJNIKeySafety(JNI_KEY_AES_KEY_TYPE)

    private fun getJNIKeySafety(type: Int): String {
        getJniKeyCounter++
        return try {
            getJNIKey(type = type)
        } catch (e: Exception) {
            getJniKeyCounter = 0
            throw e
        }
    }

    companion object{
        private const val JNI_KEY_AES_KEY_TYPE = 0
    }
}