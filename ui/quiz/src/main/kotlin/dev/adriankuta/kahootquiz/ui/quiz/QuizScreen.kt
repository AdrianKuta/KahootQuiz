package dev.adriankuta.kahootquiz.ui.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.adriankuta.kahootquiz.core.designsystem.Grey
import dev.adriankuta.kahootquiz.core.designsystem.KahootQuizTheme
import dev.adriankuta.kahootquiz.domain.models.Choice
import dev.adriankuta.kahootquiz.domain.models.Question
import kotlin.time.Duration.Companion.seconds
import dev.adriankuta.kahootquiz.core.designsystem.R as DesignR

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizScreen(
        uiState = uiState,
        modifier = modifier.fillMaxSize()
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = DesignR.drawable.bg_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Toolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(8.dp)
            )
            QuestionContent(
                question = uiState.currentQuestion ?: return,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Choices(
                choices = uiState.currentQuestion.choices ?: emptyList() // TODO remove empty list
            )
        }
    }
}

@Composable
private fun Toolbar(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "2/24",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(60.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(60.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Image(
                painter = painterResource(id = DesignR.drawable.ic_type),
                contentDescription = "",
                modifier = Modifier.size(24.dp)
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.quiz),
            )
        }
    }
}

@Composable
private fun QuestionContent(
    question: Question,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = question.image,
            contentDescription = question.imageMetadata?.altText,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = question.question ?: "",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp, vertical = 16.dp)
        )
    }
}

@Composable
private fun Choices(
    choices: List<Choice>
) {
    LazyVerticalGrid() { }
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
                Choice(answer = "Rome", correct = false)
            ),
            pointsMultiplier = 1,
            time = 30.seconds,
            questionFormat = 0,
            imageMetadata = null,
        )
        QuizScreen(
            uiState = QuizUiState(currentQuestion = sampleQuestion)
        )
    }
}
