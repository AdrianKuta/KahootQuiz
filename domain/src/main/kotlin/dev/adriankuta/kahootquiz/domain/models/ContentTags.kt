package dev.adriankuta.kahootquiz.domain.models

// Content tags domain model

data class ContentTags(
    val curriculumCodes: List<String>?,
    val generatedCurriculumCodes: List<String>?,
)