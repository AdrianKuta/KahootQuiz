package dev.adriankuta.kahootquiz

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.adriankuta.kahootquiz.ui.quiz.navigation.QuizRoute
import dev.adriankuta.kahootquiz.ui.quiz.navigation.quizScreen

@Composable
fun KahootQuizNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = QuizRoute,
        modifier = modifier
    ) {
        quizScreen()
    }
}