package com.ansssiaz.feature.film_information.data

import com.ansssiaz.data.FilmsApi
import com.ansssiaz.feature.film_information.domain.Film
import com.ansssiaz.feature.film_information.domain.FilmRepository
import com.ansssiaz.feature.film_information.domain.Schedule

class FilmRepositoryImpl(private val api: FilmsApi) : FilmRepository {
    override suspend fun getFilm(filmId: Long): Film = api.getFilm(filmId).film.toFilm()
    override suspend fun getSchedule(filmId: Long): List<Schedule> =
        api.getSchedule(filmId).schedules.map { it.toSchedule() }
}