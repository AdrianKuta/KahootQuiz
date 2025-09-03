package dev.adriankuta.kahootquiz.domain.models

data class Choice(
    val answer: String?,
    val correct: Boolean,
    val languageInfo: LanguageInfo? = null,
)