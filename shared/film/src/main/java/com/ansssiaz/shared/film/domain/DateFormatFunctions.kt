package com.ansssiaz.shared.film.domain

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
    return LocalDate.parse(this, formatter)
}

fun LocalDate.formatDateForScheduleTabs(): String {
    val dayOfWeekShort = this.dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault())
    val dayOfMonth = this.dayOfMonth
    val monthShort = this.month.getDisplayName(TextStyle.SHORT, Locale.getDefault()).lowercase()
    return "$dayOfWeekShort, $dayOfMonth $monthShort"
}