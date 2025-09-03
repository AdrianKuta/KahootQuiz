package dev.adriankuta.kahootquiz.domain.models

data class Video(
    val id: String? = null,
    val startTime: Int?,
    val endTime: Int?,
    val service: String?,
    val fullUrl: String?,
)