package dev.adriankuta.kahootquiz.model.data.mappers

import dev.adriankuta.kahootquiz.core.network.models.QuizResponseDto
import dev.adriankuta.kahootquiz.domain.models.Quiz

internal fun QuizResponseDto.toDomainModel(): Quiz =
    Quiz(this.uuid ?: "")
