package com.example.bitazzademo

import android.content.Context
import android.content.SharedPreferences
import com.example.bitazzademo.data.AuthenticationRepositoryImpl
import com.example.bitazzademo.data.AuthenticationService
import com.example.bitazzademo.domain.AuthenticationRepository
import com.example.bitazzademo.domain.LoginUseCase
import com.example.bitazzademo.domain.SHARED_PREFERENCES_NAME
import com.example.bitazzademo.domain.manager.AppKeyable
import com.example.bitazzademo.domain.manager.AppKeyer
import com.example.bitazzademo.domain.pref.PreferenceStoragable
import com.example.bitazzademo.domain.pref.PreferenceStorager
import com.example.bitazzademo.encryption.AppEncryptable
import com.example.bitazzademo.encryption.AppEncrypter
import com.example.bitazzademo.network.NetworkManager
import com.example.bitazzademo.network.Networkable
import com.example.bitazzademo.service.BitazzaHttpClient
import com.example.bitazzademo.ui.account.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single<SharedPreferences>(named(SHARED_PREFERENCES_NAME)) {
        androidContext().applicationContext.getSharedPreferences(
            "SharedPreferences1",
            Context.MODE_PRIVATE
        )
    }
    single<AppKeyable> { AppKeyer() }
    single<AppEncryptable> { AppEncrypter() }
    single<PreferenceStoragable> {
        PreferenceStorager(
            get(named(SHARED_PREFERENCES_NAME)),
            get(),
            get()
        )
    }

    single<Networkable> { NetworkManager.init(get()) }
    single(named("RETROFIT")) {
        BitazzaHttpClient().createRetrofit(context = androidContext(), get())
    }

    single { get<Retrofit>(named("RETROFIT")).create(AuthenticationService::class.java) }
    single<AuthenticationRepository> { AuthenticationRepositoryImpl(get(), get()) }

    factory { LoginUseCase(get()) }

    viewModel { LoginViewModel(get()) }
}