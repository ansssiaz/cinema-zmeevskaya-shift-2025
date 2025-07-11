package com.ansssiaz.feature.film_information.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.feature.film_information.domain.FilmRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmViewModel(
    filmId: Long,
    private val repository: FilmRepository
) : ViewModel() {
    private val _state = MutableStateFlow(FilmUiState())
    val state = _state.asStateFlow()

    init {
        getFilm(filmId)
    }

    private fun getFilm(filmId: Long) {
        viewModelScope.launch {
            val film = repository.getFilm(filmId)
            val schedule = repository.getSchedule(filmId)
            _state.update {
                it.copy(
                    film = film,
                    schedules = schedule
                )
            }
        }
    }
}