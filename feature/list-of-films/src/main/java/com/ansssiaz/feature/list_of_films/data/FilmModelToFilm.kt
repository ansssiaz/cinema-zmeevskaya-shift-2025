package com.ansssiaz.feature.list_of_films.data

import com.ansssiaz.data.FilmModel
import com.ansssiaz.data.toDisplayString
import com.ansssiaz.feature.list_of_films.domain.Film
import com.ansssiaz.shared.film.domain.getFullImageUrl
import com.ansssiaz.shared.film.domain.getYearFromReleaseDate

fun FilmModel.toFilm() = Film(
    id = id.toLong(),
    name = name,
    ageRating = ageRating.toDisplayString(),
    genres = genres,
    country = country.name,
    releaseYear = getYearFromReleaseDate(releaseDate),
    kinopoiskRating = userRatings.kinopoisk.toFloat(),
    image = getFullImageUrl(img)
)