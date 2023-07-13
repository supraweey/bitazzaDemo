package com.example.bitazzademo.domain.pref

import android.content.SharedPreferences
import com.example.bitazzademo.domain.manager.AppKeyable
import com.example.bitazzademo.encryption.AppEncryptable
import timber.log.Timber

class PreferenceStorager constructor(
    private val prefs: SharedPreferences,
    private val encryptable: AppEncryptable,
    private val keyable: AppKeyable
) : PreferenceStoragable {
    override fun getString(key: String, defaultValue: String): String = get(key) ?: defaultValue

    override fun putString(key: String, value: String) {
        put(key, value)
    }

    override fun getInt(key: String, defaultValue: Int): Int = get(key)?.toIntOrNull() ?: defaultValue

    override fun putInt(key: String, value: Int) {
        put(key, value)
    }

    override fun delete(key: String) {
        prefs.edit().remove(key).apply()
    }

    private fun get(key: String): String? {
        return try {
            val encryptedPref = prefs.getString(key, null)
            encryptedPref?.let {
                encryptable.decryptAES(encryptedPref, keyable.aesKey)
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun <T> put(key: String, value: T?) {
        try {
            var encryptedData: String? = null
            value?.let {
                encryptedData = encryptable.encryptAES(value.toString(), keyable.aesKey)
            }

            prefs.edit().putString(key, encryptedData).apply()

        } catch (e: Exception) {
            Timber.e(e, "ERROR ~> : ${e.message}")
        }
    }
}