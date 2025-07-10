package com.ansssiaz.feature.list_of_films.domain

data class Film(
    val id: Long,
    val name: String,
    val ageRating: String,
    val genres: List<String>,
    val country: String,
    val releaseYear: String,
    val kinopoiskRating: Float,
    val image: String
)
