package com.ansssiaz.shared.film.domain

import com.ansssiaz.shared.film.core.BASE_URL

fun getFullImageUrl(imagePath: String): String {
    val cleanImagePath = imagePath.trimStart('/')
    return "$BASE_URL$cleanImagePath"
}