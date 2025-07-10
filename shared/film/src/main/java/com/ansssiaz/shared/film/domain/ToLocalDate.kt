package com.ansssiaz.shared.film.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    return LocalDate.parse(this, formatter)
}