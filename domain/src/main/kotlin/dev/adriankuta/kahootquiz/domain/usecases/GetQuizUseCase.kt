package dev.adriankuta.kahootquiz.domain.usecases

import dev.adriankuta.kahootquiz.domain.models.Quiz
import dev.adriankuta.kahootquiz.domain.repositories.QuizRepository
import javax.inject.Inject

class GetQuizUseCase @Inject constructor(
    private val quizRepository: QuizRepository,
) {

    suspend operator fun invoke(): Quiz {
        return quizRepository.getQuiz()
    }
}
