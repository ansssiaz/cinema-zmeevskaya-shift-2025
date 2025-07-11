package com.ansssiaz.feature.film_information.presentation.viewmodel

import com.ansssiaz.feature.film_information.domain.Film
import com.ansssiaz.feature.film_information.domain.Schedule

data class FilmUiState(
    val film: Film? = null,
    val schedules: List<Schedule>? = null
)