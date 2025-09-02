package dev.adriankuta.kahootquiz.model.data

import dev.adriankuta.kahootquiz.core.network.retrofit.QuizApi
import dev.adriankuta.kahootquiz.domain.models.Quiz
import dev.adriankuta.kahootquiz.domain.repositories.QuizRepository
import dev.adriankuta.kahootquiz.model.data.mappers.toDomainModel
import javax.inject.Inject

internal class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi
) : QuizRepository {

    override suspend fun getQuiz(): Quiz {
        return quizApi.getQuiz().toDomainModel()
    }
}
