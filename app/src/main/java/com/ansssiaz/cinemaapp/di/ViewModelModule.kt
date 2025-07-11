package com.ansssiaz.cinemaapp.di

import com.ansssiaz.feature.film_information.presentation.viewmodel.FilmViewModel
import com.ansssiaz.feature.list_of_films.presentation.viewmodel.FilmsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FilmsViewModel(get())
    }

    viewModel { (filmId: Long) ->
        FilmViewModel(filmId, get())
    }
}