package dev.adriankuta.kahootquiz.domain.models

import kotlin.time.Duration

// Question and choice related domain models

data class Question(
    val type: String?,
    val question: String?,
    val time: Duration?,
    val points: Boolean? = null,
    val pointsMultiplier: Int?,
    val choices: List<Choice>?,
    val layout: String? = null,
    val image: String,
    val imageMetadata: ImageMetadata?,
    val resources: String? = null,
    val video: Video? = null,
    val questionFormat: Int? = null,
    val languageInfo: LanguageInfo? = null,
    val media: List<MediaItem>? = null,
    val choiceRange: ChoiceRange? = null
)