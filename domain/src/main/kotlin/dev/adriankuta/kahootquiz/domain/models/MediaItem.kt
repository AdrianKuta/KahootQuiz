package dev.adriankuta.kahootquiz.domain.models

// Generic media item on question

data class MediaItem(
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
    val crop: Crop? = null
)