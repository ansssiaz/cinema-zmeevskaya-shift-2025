package com.ansssiaz.data

import kotlinx.serialization.Serializable

@Serializable
data class FilmsResponse(
    val success: Boolean,
    val films: List<FilmModel>
)

@Serializable
data class FilmResponse(
    val success: Boolean,
    val film: FilmModel
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
    val ageRating: AgeRating,
    val genres: List<String>,
    val userRatings: UserRatings,
    val img: String,
    val country: Country
)

enum class AgeRating {
    G,
    PG,
    PG13,
    R,
    NC17,
}

fun AgeRating.toDisplayString() = when (this) {
    AgeRating.G -> "0+"
    AgeRating.PG -> "6+"
    AgeRating.PG13 -> "12+"
    AgeRating.R -> "16+"
    AgeRating.NC17 -> "18+"
}

@Serializable
data class Person(
    val id: String,
    val professions: List<Professions>,
    val fullName: String
)

enum class Professions {
    ACTOR,
    DIRECTOR
}

@Serializable
data class UserRatings(
    val kinopoisk: String,
    val imdb: String
)

@Serializable
data class Country(
    val id: Int,
    val code: String,
    val code2: String,
    val name: String
)