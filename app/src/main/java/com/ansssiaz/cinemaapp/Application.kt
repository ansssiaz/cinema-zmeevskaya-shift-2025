package com.ansssiaz.cinemaapp

import android.app.Application
import com.ansssiaz.cinemaapp.di.apiModule
import com.ansssiaz.cinemaapp.di.repositoryModule
import com.ansssiaz.cinemaapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(listOf(apiModule, repositoryModule, viewModelModule))
        }
    }
}