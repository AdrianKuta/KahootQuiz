package dev.adriankuta.kahootquiz.domain.models

// Common simple models reused across the domain layer

data class LanguageInfo(
    val language: String?,
    val lastUpdatedOn: Long?,
    val readAloudSupported: Boolean?,
)
