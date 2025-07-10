package com.ansssiaz.feature.film_information.domain

data class Film(
    val id: Long,
    val name: String,
    val description: String,
    val ageRating: String,
    val genres: List<String>,
    val country: String,
    val releaseYear: String,
    val kinopoiskRating: Float,
    val image: String
)
