package com.ansssiaz.cinemaapp.di

import com.ansssiaz.feature.list_of_films.data.FilmsRepositoryImpl
import com.ansssiaz.feature.list_of_films.domain.FilmsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideFilmsRepository(get()) }

    single<FilmsRepository> {
        return@single FilmsRepositoryImpl(get())
    }
}

private fun provideFilmsRepository(repository: FilmsRepositoryImpl): FilmsRepository = repository