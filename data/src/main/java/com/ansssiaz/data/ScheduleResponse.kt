package com.ansssiaz.data

import kotlinx.serialization.Serializable

@Serializable
data class ScheduleResponse(
    val success: Boolean,
    val reason: String? = null,
    val schedules: List<SchedulesModel>
)

@Serializable
data class SchedulesModel(
    val date: String,
    val seances: List<SeanceModel>
)

@Serializable
data class SeanceModel(
    val time: String,
    val hall: HallModel
)

@Serializable
data class HallModel(
    val name: String,
    val places: List<List<PlaceModel>>
)

@Serializable
data class PlaceModel(
    val type: String,
    val price: Int
)
