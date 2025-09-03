package dev.adriankuta.kahootquiz.core.network.models

// Cover metadata and related DTOs

data class CoverMetadataDto(
    val id: String?,
    val altText: String?,
    val contentType: String?,
    val origin: String?,
    val externalRef: String?,
    val resources: String?,
    val width: Int?,
    val height: Int?,
    val extractedColors: List<ExtractedColorDto>?,
    val blurhash: String?,
    val crop: CropDto?,
)

// Color extracted from cover image

data class ExtractedColorDto(
    val swatch: String?,
    val rgbHex: String?,
)

// Crop descriptor

data class CropDto(
    val origin: PointDto?,
    val target: PointDto?,
    val circular: Boolean?,
)
