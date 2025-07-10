package com.ansssiaz.data

import retrofit2.http.GET

interface FilmsApi {
    @GET("cinema/films")
    suspend fun getFilms(): FilmsResponse
}