package dev.adriankuta.kahootquiz.domain.models

import kotlin.time.Duration

// Question and choice related domain models

data class Question(
    val type: String?,
    val question: String?,
    val time: Duration?,
    val points: Boolean?,
    val pointsMultiplier: Int?,
    val choices: List<Choice>?,
    val layout: String?,
    val image: String?,
    val imageMetadata: ImageMetadata?,
    val resources: String?,
    val video: Video?,
    val questionFormat: Int?,
    val languageInfo: LanguageInfo?,
    val media: List<MediaItem>?,
    val choiceRange: ChoiceRange?
)