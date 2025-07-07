package com.ansssiaz.feature.list_of_films.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ansssiaz.feature.list_of_films.domain.FilmsRepository
import com.ansssiaz.feature.list_of_films.util.Status
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsViewModel(private val repository: FilmsRepository) : ViewModel() {
    private val _state = MutableStateFlow(FilmsUiState())
    val state = _state.asStateFlow()

    init {
        getFilms()
    }

    fun getFilms() {
        _state.update {
            it.copy(
                status = Status.Loading
            )
        }
        viewModelScope.launch {
            try {
                val films = repository.getFilms()
                _state.update {
                    it.copy(
                        films = films,
                        status = Status.Idle
                    )
                }
            } catch (e: Exception) {
                _state.update {
                    it.copy(status = Status.Error(e))
                }
            }
        }
    }
}