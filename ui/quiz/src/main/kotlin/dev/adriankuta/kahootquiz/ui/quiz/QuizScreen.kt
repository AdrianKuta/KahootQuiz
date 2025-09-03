package dev.adriankuta.kahootquiz.ui.quiz

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    modifier: Modifier = Modifier,
) {

}
