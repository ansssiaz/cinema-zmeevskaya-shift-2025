package com.ansssiaz.shared.film.domain

fun getYearFromReleaseDate(dateStr: String): String {
    return dateStr.trim().split(" ").last()
}