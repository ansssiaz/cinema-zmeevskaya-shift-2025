package com.ansssiaz.feature.film_information.data

import com.ansssiaz.data.HallModel
import com.ansssiaz.data.PlaceModel
import com.ansssiaz.data.SchedulesModel
import com.ansssiaz.data.SeanceModel
import com.ansssiaz.feature.film_information.domain.Hall
import com.ansssiaz.feature.film_information.domain.Place
import com.ansssiaz.feature.film_information.domain.Schedule
import com.ansssiaz.feature.film_information.domain.Seance
import com.ansssiaz.shared.film.domain.toLocalDate

fun SchedulesModel.toSchedule() = Schedule(
    date = date.toLocalDate(),
    seances = seances.map { it.toSeance() }
)

fun SeanceModel.toSeance() = Seance(
    time = time,
    hall = hall.toHall()
)

fun HallModel.toHall() = Hall(
    name = name,
    places = places.map { row -> row.map { it.toPlace() } }
)

fun PlaceModel.toPlace() = Place(
    type = type,
    price = price
)