package com.ansssiaz.feature.film_information.domain

import java.time.LocalDate

data class Schedule(
    val date: LocalDate,
    val seances: List<Seance>
)

data class Seance(
    val time: String,
    val hall: Hall
)

data class Hall(
    val name: String,
    val places: List<List<Place>>
)

data class Place(
    val type: String,
    val price: Int
)