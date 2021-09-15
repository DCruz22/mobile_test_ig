package com.example.koombea_ig

import android.app.Application
import com.example.koombea_ig.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(Modules.all)
        }
    }
}