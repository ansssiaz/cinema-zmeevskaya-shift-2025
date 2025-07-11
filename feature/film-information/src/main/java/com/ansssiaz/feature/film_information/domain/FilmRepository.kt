package com.ansssiaz.feature.film_information.domain

interface FilmRepository {
    suspend fun getFilm(filmId: Long): Film
    suspend fun getSchedule(filmId: Long): List<Schedule>
}