package dev.adriankuta.kahootquiz.domain.models

// Slider range for "slider" question type

data class ChoiceRange(
    val start: Int?,
    val end: Int?,
    val step: Int?,
    val correct: Int?,
    val tolerance: Int?
)