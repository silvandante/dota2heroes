package com.walker.finalappdotaheros

import android.app.Application
import com.walker.finalappdotaheros.module.FinalAppDotaHerosDI
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ApplicationDota: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ApplicationDota)
            modules(FinalAppDotaHerosDI.modules)
        }

    }

}