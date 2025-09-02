package dev.adriankuta.kahootquiz.core.network.model

// Commonly reused DTOs

data class LanguageInfoDto(
    val language: String?,
    val lastUpdatedOn: Long?,
    val readAloudSupported: Boolean?
)

// Minimal channel info

data class ChannelDto(val id: String?)

// Geometry helpers

data class PointDto(
    val x: Int?,
    val y: Int?
)
