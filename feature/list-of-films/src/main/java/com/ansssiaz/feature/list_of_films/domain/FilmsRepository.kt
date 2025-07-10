package com.ansssiaz.feature.list_of_films.domain

interface FilmsRepository {
    suspend fun getFilms(): List<Film>
}