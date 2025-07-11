package com.ansssiaz.feature.list_of_films.data

import com.ansssiaz.data.FilmsApi
import com.ansssiaz.feature.list_of_films.domain.FilmsRepository

class FilmsRepositoryImpl(private val api: FilmsApi) : FilmsRepository {
    override suspend fun getFilms() = api.getFilms()
        .films
        .map {
            it.toFilm()
        }
}