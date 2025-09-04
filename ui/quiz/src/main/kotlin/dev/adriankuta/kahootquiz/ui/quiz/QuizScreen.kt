package dev.adriankuta.kahootquiz.ui.quiz

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.adriankuta.kahootquiz.core.designsystem.Grey
import dev.adriankuta.kahootquiz.core.designsystem.KahootQuizTheme
import dev.adriankuta.kahootquiz.domain.models.Choice
import dev.adriankuta.kahootquiz.domain.models.Question
import dev.adriankuta.kahootquiz.ui.quiz.components.AnswerFeedbackBanner
import dev.adriankuta.kahootquiz.ui.quiz.components.Choices
import dev.adriankuta.kahootquiz.ui.quiz.components.QuestionContent
import dev.adriankuta.kahootquiz.ui.quiz.components.TimerBar
import dev.adriankuta.kahootquiz.ui.quiz.components.Toolbar
import kotlin.time.Duration.Companion.seconds

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizScreenViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizScreen(
        uiState = uiState,
        onSelect = viewModel::onChoiceSelected,
        onContinue = viewModel::onContinue,
        modifier = modifier.fillMaxSize(),
    )
}

@Composable
private fun QuizScreen(
    uiState: ScreenUiState,
    onSelect: (Int) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        when (uiState) {
            ScreenUiState.Loading -> QuizScreenLoading()
            is ScreenUiState.Success -> QuizScreenSuccess(
                uiState = uiState,
                onSelect = onSelect,
                onContinue = onContinue,
            )
        }
    }
}

@Composable
private fun QuizScreenLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier,
    )
}

@Composable
private fun QuizScreenSuccess(
    uiState: ScreenUiState.Success,
    onSelect: (Int) -> Unit,
    onContinue: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
    ) {
        toolbar(uiState)
        questionContent(uiState)
        choices(uiState, onSelect)
        // Timer below choices
        if (uiState.selectedChoiceIndex == null && uiState.timerState.totalTimeSeconds > 0) {
            timer(uiState)
        } else {
            continueButton(uiState, onContinue)
        }
    }
}

private fun LazyListScope.toolbar(
    uiState: ScreenUiState.Success,
    modifier: Modifier = Modifier,
) {
    item(key = "toolbar") {
        Box(
            modifier = modifier
                .height(72.dp),
        ) {
            Toolbar(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                currentQuestionIndex = uiState.currentQuestionIndex,
                totalQuestions = uiState.totalQuestions,
            )
            uiState.isAnswerCorrect?.let { isCorrect ->
                AnswerFeedbackBanner(
                    isCorrect = isCorrect,
                )
            }
        }
    }
}

private fun LazyListScope.questionContent(
    uiState: ScreenUiState.Success,
) {
    if (uiState.currentQuestion != null) {
        item(key = "question_${uiState.currentQuestionIndex}") {
            QuestionContent(
                question = uiState.currentQuestion,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .animateItem(),
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}

private fun LazyListScope.timer(uiState: ScreenUiState.Success) {
    item(key = "timer_${uiState.currentQuestionIndex}") {
        TimerBar(
            totalSeconds = uiState.timerState.totalTimeSeconds,
            remainingSeconds = uiState.timerState.remainingTimeSeconds,
            modifier = Modifier.padding(8.dp),
        )
    }
}

private fun LazyListScope.continueButton(
    uiState: ScreenUiState.Success,
    onContinue: () -> Unit,
) {
    item(key = "continue_${uiState.currentQuestionIndex}") {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            FilledTonalButton(
                onClick = onContinue,
                colors = ButtonDefaults.filledTonalButtonColors().copy(
                    containerColor = Grey,
                    contentColor = Color.Black,
                ),
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = stringResource(R.string.continue_text),
                )
            }
        }
    }
}

private fun LazyListScope.choices(
    uiState: ScreenUiState.Success,
    onSelect: (Int) -> Unit,
) {
    uiState.currentQuestion?.choices?.let { choicesList ->
        if (choicesList.isNotEmpty()) {
            item(key = "choices_${uiState.currentQuestionIndex}") {
                Choices(
                    choices = choicesList,
                    selectedChoiceIndex = uiState.selectedChoiceIndex,
                    onSelect = onSelect,
                    modifier = Modifier.padding(8.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenPreview() {
    KahootQuizTheme {
        val sampleQuestion = Question(
            type = "quiz",
            image = "", // Add a sample image URL or leave empty
            question = "What is the capital of France?",
            choices = listOf(
                Choice(answer = "Berlin", correct = false),
                Choice(answer = "Madrid", correct = false),
                Choice(answer = "Paris", correct = true),
                Choice(answer = "Rome", correct = false),
            ),
            pointsMultiplier = 1,
            time = 30.seconds,
            questionFormat = 0,
            imageMetadata = null,
        )
        QuizScreen(
            uiState = ScreenUiState.Success(
                currentQuestion = sampleQuestion,
                selectedChoiceIndex = null,
                totalQuestions = 12,
            ),
            onSelect = {},
            onContinue = {},
        )
    }
}

@Preview
@Composable
private fun QuizScreenRevealedAnswerPreview() {
    KahootQuizTheme {
        val sampleQuestion = Question(
            type = "quiz",
            image = "", // Add a sample image URL or leave empty
            question = "What is the capital of France?",
            choices = listOf(
                Choice(answer = "Berlin", correct = false),
                Choice(answer = "Madrid", correct = false),
                Choice(answer = "Paris", correct = true),
                Choice(answer = "Rome", correct = false),
            ),
            pointsMultiplier = 1,
            time = 30.seconds,
            questionFormat = 0,
            imageMetadata = null,
        )
        QuizScreen(
            uiState = ScreenUiState.Success(
                currentQuestion = sampleQuestion,
                selectedChoiceIndex = 1,
                totalQuestions = 12,
            ),
            onSelect = {},
            onContinue = {},
        )
    }
}
