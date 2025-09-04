package dev.adriankuta.kahootquiz.data

import dev.adriankuta.kahootquiz.core.network.retrofit.QuizApi
import dev.adriankuta.kahootquiz.data.mappers.toDomainModel
import dev.adriankuta.kahootquiz.domain.models.Quiz
import dev.adriankuta.kahootquiz.domain.repositories.QuizRepository
import javax.inject.Inject

internal class QuizRepositoryImpl @Inject constructor(
    private val quizApi: QuizApi,
) : QuizRepository {

    override suspend fun getQuiz(): Quiz {
        return quizApi.getQuiz().toDomainModel()
    }
}
