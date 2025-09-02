package dev.adriankuta.kahootquiz.domain.repositories

import dev.adriankuta.kahootquiz.domain.models.Quiz

interface QuizRepository {
    suspend fun getQuiz(): Quiz
}
