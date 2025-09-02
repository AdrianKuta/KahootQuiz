package dev.adriankuta.kahootquiz.core.network.models

// Question and choice related DTOs

data class QuestionDto(
    val type: String?,
    val question: String?,
    val time: Int?,
    val points: Boolean?,
    val pointsMultiplier: Int?,
    val choices: List<ChoiceDto>?,
    val layout: String?,
    val image: String?,
    val imageMetadata: ImageMetadataDto?,
    val resources: String?,
    val video: VideoDto?,
    val questionFormat: Int?,
    val languageInfo: LanguageInfoDto?,
    val media: List<MediaItemDto>?,
    val choiceRange: ChoiceRangeDto?
)

// Choice option

data class ChoiceDto(
    val answer: String?,
    val correct: Boolean?,
    val languageInfo: LanguageInfoDto?
)

// Optional video attachment

data class VideoDto(
    val id: String? = null,
    val startTime: Int?,
    val endTime: Int?,
    val service: String?,
    val fullUrl: String?
)

// Image metadata appearing in multiple places

data class ImageMetadataDto(
    val id: String?,
    val altText: String? = null,
    val contentType: String?,
    val origin: String? = null,
    val externalRef: String? = null,
    val resources: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val effects: List<String>? = null,
    val crop: CropDto? = null
)

// Generic media item on question

data class MediaItemDto(
    val type: String?,
    val zIndex: Int?,
    val isColorOnly: Boolean?,
    val id: String?,
    val altText: String? = null,
    val contentType: String?,
    val origin: String? = null,
    val externalRef: String? = null,
    val resources: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val crop: CropDto? = null
)

// Slider range for "slider" question type

data class ChoiceRangeDto(
    val start: Int?,
    val end: Int?,
    val step: Int?,
    val correct: Int?,
    val tolerance: Int?
)
