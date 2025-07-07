package com.ansssiaz.list_of_films.domain

interface FilmsRepository {
    suspend fun getFilms()
}