package com.example.bitazzademo

import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setUpKoin()
    }
    private fun setUpKoin() {
        startKoin{
            androidContext(this@Application)

            val allModule = arrayListOf(appModule)
            modules(allModule)
        }
    }
}