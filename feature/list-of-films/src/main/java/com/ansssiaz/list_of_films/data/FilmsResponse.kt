package com.ansssiaz.list_of_films.data

import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponse(
    val success: Boolean,
    val films: List<FilmModel>
)

@Serializable
data class FilmModel(
    val id: String,
    val name: String,
    val originalName: String,
    val description: String,
    val releaseDate: String,
    val actors: List<Person>,
    val directors: List<Person>,
    val runtime: Int,
    val ageRating: String,
    val genres: List<String>,
    val userRatings: UserRatings,
    val img: String,
    val country: Country
)

@Serializable
data class Person(
    val id: String,
    val professions: List<String>,
    val fullName: String
)

@Serializable
data class UserRatings(
    val kinopoisk: String,
    val imdb: String
)

@Serializable
data class Country(
    val id: Int,
    val ode: String,
    val code2: String,
    val name: String
)