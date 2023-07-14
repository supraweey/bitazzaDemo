package com.example.bitazzademo.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitazzademo.databinding.ActivitySplashBinding
import com.example.bitazzademo.domain.USER_KEY_TOKEN
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.ui.account.AccountActivity
import com.example.bitazzademo.ui.main.MainActivity
import org.koin.android.ext.android.inject

class SplashActivity : AppCompatActivity() {
    private val prefs: PreferenceStoragable by inject()
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        checkLogin()
    }

    private fun checkLogin() {
        if (prefs.getString(USER_KEY_TOKEN, "").isEmpty()) {
            AccountActivity.startActivity(this)
        } else {
            MainActivity.startActivity(this)
        }
    }
}