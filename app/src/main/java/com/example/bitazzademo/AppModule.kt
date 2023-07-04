package com.example.bitazzademo

import com.example.bitazzademo.data.AccountService
import com.example.bitazzademo.data.AccountRepositoryImpl
import com.example.bitazzademo.domain.AccountRepository
import com.example.bitazzademo.network.NetworkManager
import com.example.bitazzademo.network.Networkable
import com.example.bitazzademo.service.BitazzaHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {

    single<Networkable> { NetworkManager.init(get()) }
    single(named("RETROFIT")) {
        BitazzaHttpClient().createRetrofit(context = androidContext())
    }

    single { get<Retrofit>(named("RETROFIT")).create(AccountService::class.java)  }
    single<AccountRepository> { AccountRepositoryImpl(get(), get()) }

//    factory { MoviePopularUseCase(get()) }
//
//    viewModel{ PopularViewModel(get()) }
}