package com.ansssiaz.data

import retrofit2.http.GET
import retrofit2.http.Path

interface FilmsApi {
    @GET("cinema/films")
    suspend fun getFilms(): FilmsResponse

    @GET("cinema/film/{filmId}")
    suspend fun getFilm(@Path("filmId") filmId: Long): FilmResponse

    @GET("cinema/film/{filmId}/schedule")
    suspend fun getSchedule(@Path("filmId") filmId: Long): ScheduleResponse
}