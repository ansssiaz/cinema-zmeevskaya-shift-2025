package com.ansssiaz.feature.list_of_films.data

import retrofit2.http.GET

interface FilmsApi {
    @GET("cinema/films")
    suspend fun getFilms(): FilmsResponse
}