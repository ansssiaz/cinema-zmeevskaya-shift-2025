package com.ansssiaz.feature.list_of_films.presentation.viewmodel

import com.ansssiaz.feature.list_of_films.domain.Film
import com.ansssiaz.feature.list_of_films.util.Status

data class FilmsUiState (
    val films: List<Film>? = null,
    val status: Status = Status.Idle
){
    val isLoading: Boolean = status == Status.Loading && films.isNullOrEmpty()
    val isError: Boolean
        get() = status is Status.Error && films.isNullOrEmpty()
}