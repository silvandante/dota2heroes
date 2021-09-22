package com.walker.finalappdotaheros.module

import com.walker.finalappdotaheros.data.LikeDatabase.Companion.getDatabase
import com.walker.finalappdotaheros.data.LikeRepository
import com.walker.finalappdotaheros.network.RetrofitInstance
import com.walker.finalappdotaheros.network.ApiRepository
import com.walker.finalappdotaheros.network.ApiService
import com.walker.finalappdotaheros.viewmodels.HeroViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

class FinalAppDotaHerosDI {

    companion object {
        var modules = mutableListOf(
                module {
                    factory { RetrofitInstance.createService(ApiService::class.java) }
                    single { ApiRepository(get()) }
                    single { LikeRepository(getDatabase(androidApplication()).likeDao()) }
                    viewModel { HeroViewModel(get(), get()) }
                })
    }

}