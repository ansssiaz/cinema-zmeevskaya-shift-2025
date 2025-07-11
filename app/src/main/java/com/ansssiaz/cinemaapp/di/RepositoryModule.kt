package com.ansssiaz.cinemaapp.di

import com.ansssiaz.feature.film_information.data.FilmRepositoryImpl
import com.ansssiaz.feature.film_information.domain.FilmRepository
import com.ansssiaz.feature.list_of_films.data.FilmsRepositoryImpl
import com.ansssiaz.feature.list_of_films.domain.FilmsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { provideFilmsRepository(get()) }
    single { provideFilmRepository(get()) }

    single<FilmsRepository> {
        return@single FilmsRepositoryImpl(get())
    }

    single<FilmRepository> {
        return@single FilmRepositoryImpl(get())
    }
}

private fun provideFilmsRepository(repository: FilmsRepositoryImpl): FilmsRepository = repository
private fun provideFilmRepository(repository: FilmRepositoryImpl): FilmRepository = repository