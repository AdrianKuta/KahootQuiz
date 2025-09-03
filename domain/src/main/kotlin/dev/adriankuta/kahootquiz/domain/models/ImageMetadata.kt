package dev.adriankuta.kahootquiz.domain.models

// Image metadata appearing in multiple places

data class ImageMetadata(
    val id: String?,
    val altText: String? = null,
    val contentType: String?,
    val origin: String? = null,
    val externalRef: String? = null,
    val resources: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val effects: List<String>? = null,
    val crop: Crop? = null
)