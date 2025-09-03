package dev.adriankuta.kahootquiz.ui.quiz.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object QuizRoute

fun NavGraphBuilder.quizScreen() {
    composable<QuizRoute> {

    }
}