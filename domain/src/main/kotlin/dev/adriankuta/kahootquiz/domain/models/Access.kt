package dev.adriankuta.kahootquiz.domain.models

// Access settings

data class Access(
    val groupRead: List<String>?,
    val folderGroupIds: List<String>?,
    val features: List<String>?
)