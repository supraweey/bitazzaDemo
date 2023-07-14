package com.example.bitazzademo

import android.content.Context
import android.content.SharedPreferences
import com.example.bitazzademo.data.BitazzaRepositoryImpl
import com.example.bitazzademo.data.BitazzaService
import com.example.bitazzademo.domain.BitazzaRepository
import com.example.bitazzademo.domain.GetProductListUseCase
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
import com.example.bitazzademo.ui.main.market.MarketViewModel
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
        BitazzaHttpClient().createRetrofit(get())
    }

    single { get<Retrofit>(named("RETROFIT")).create(BitazzaService::class.java) }
    single<BitazzaRepository> { BitazzaRepositoryImpl(get(), get()) }

    factory { LoginUseCase(get()) }
    factory { GetProductListUseCase(get()) }

    viewModel { LoginViewModel(get()) }
    viewModel { MarketViewModel(get(), get()) }
}