package com.ansssiaz.list_of_films.data

import retrofit2.http.GET

interface FilmsApi {
    @GET("/cinema/today")
    suspend fun getFilms(): FilmsResponse
}