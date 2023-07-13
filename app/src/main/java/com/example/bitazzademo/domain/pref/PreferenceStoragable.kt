package com.example.bitazzademo.domain.pref

interface PreferenceStoragable {
    fun getString(key: String, defaultValue: String = ""): String

    fun putString(key: String, value: String)

    fun getInt(key: String, defaultValue: Int = 0): Int

    fun putInt(key: String, value: Int)

    fun delete(key: String)
}