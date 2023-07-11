package com.example.bitazzademo.domain.pref

interface PreferenceStoragable {
    fun getString(key: String, defaultValue: String = ""): String

    fun putString(key: String, value: String)
}