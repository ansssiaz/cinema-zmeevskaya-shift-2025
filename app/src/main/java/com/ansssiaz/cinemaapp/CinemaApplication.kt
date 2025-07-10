package com.ansssiaz.cinemaapp

import android.app.Application
import com.ansssiaz.cinemaapp.di.apiModule
import com.ansssiaz.cinemaapp.di.repositoryModule
import com.ansssiaz.cinemaapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CinemaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CinemaApplication)
            modules(listOf(apiModule, repositoryModule, viewModelModule))
        }
    }
}