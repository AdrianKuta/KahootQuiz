package dev.adriankuta.kahootquiz.domain.models

// Cover metadata and related domain models

data class CoverMetadata(
    val id: String?,
    val altText: String?,
    val contentType: String?,
    val origin: String?,
    val externalRef: String?,
    val resources: String?,
    val width: Int?,
    val height: Int?,
    val extractedColors: List<ExtractedColor>?,
    val blurhash: String?,
    val crop: Crop?,
)