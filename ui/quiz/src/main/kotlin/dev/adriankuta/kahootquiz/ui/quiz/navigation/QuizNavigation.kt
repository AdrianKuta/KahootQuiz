package dev.adriankuta.kahootquiz.ui.quiz.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.adriankuta.kahootquiz.ui.quiz.QuizScreen
import kotlinx.serialization.Serializable

@Serializable
data object QuizRoute

fun NavGraphBuilder.quizScreen() {
    composable<QuizRoute> {
        QuizScreen()
    }
}