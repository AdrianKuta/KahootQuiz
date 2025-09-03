package dev.adriankuta.kahootquiz.domain.models

// Crop descriptor

data class Crop(
    val origin: Point?,
    val target: Point?,
    val circular: Boolean?,
)