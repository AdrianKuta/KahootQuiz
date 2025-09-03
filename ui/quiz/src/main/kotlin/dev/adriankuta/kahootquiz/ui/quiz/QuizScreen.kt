package dev.adriankuta.kahootquiz.ui.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
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
import androidx.core.text.HtmlCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import dev.adriankuta.kahootquiz.core.designsystem.Blue2
import dev.adriankuta.kahootquiz.core.designsystem.Green
import dev.adriankuta.kahootquiz.core.designsystem.Green2
import dev.adriankuta.kahootquiz.core.designsystem.Grey
import dev.adriankuta.kahootquiz.core.designsystem.KahootQuizTheme
import dev.adriankuta.kahootquiz.core.designsystem.Pink
import dev.adriankuta.kahootquiz.core.designsystem.Red
import dev.adriankuta.kahootquiz.core.designsystem.Red2
import dev.adriankuta.kahootquiz.core.designsystem.Yellow3
import dev.adriankuta.kahootquiz.core.designsystem.contrastiveTo
import dev.adriankuta.kahootquiz.core.designsystem.toAnnotatedString
import dev.adriankuta.kahootquiz.domain.models.Choice
import dev.adriankuta.kahootquiz.domain.models.Question
import kotlin.time.Duration.Companion.seconds
import dev.adriankuta.kahootquiz.core.designsystem.R as DesignR

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    viewModel: QuizScreenViewModel = hiltViewModel(),
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    QuizScreen(
        uiState = uiState,
        onSelect = viewModel::onChoiceSelected,
        modifier = modifier.fillMaxSize(),
    )
}

@Composable
private fun QuizScreen(
    uiState: QuizUiState,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = DesignR.drawable.bg_image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Toolbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(8.dp),
            )
            QuestionContent(
                question = uiState.currentQuestion ?: return,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Spacer(Modifier.height(8.dp))
            Choices(
                choices = uiState.currentQuestion.choices ?: emptyList(), // TODO remove empty list
                answer = uiState.answer,
                onSelect = onSelect,
            )
        }
    }
}

@Composable
private fun Toolbar(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = "2/24",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(60.dp),
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(60.dp),
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Image(
                painter = painterResource(id = DesignR.drawable.ic_type),
                contentDescription = "",
                modifier = Modifier.size(24.dp),
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
        modifier = modifier,
    ) {
        AsyncImage(
            model = question.image,
            contentDescription = question.imageMetadata?.altText,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = HtmlCompat.fromHtml(
                question.question ?: "",
                HtmlCompat.FROM_HTML_MODE_COMPACT,
            ).toAnnotatedString(),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(4.dp),
                )
                .padding(horizontal = 8.dp, vertical = 16.dp),
        )
    }
}

@Composable
private fun Choices(
    choices: List<Choice>,
    onSelect: (Int) -> Unit,
    answer: AnswerUiState?,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        itemsIndexed(choices) { index, choice ->
            ChoiceItem(
                choice = choice,
                index = index,
                answer = answer,
                onClick = { onSelect(index) },
            )
        }
    }
}

@Composable
private fun ChoiceItem(
    choice: Choice,
    onClick: () -> Unit,
    index: Int,
    answer: AnswerUiState?,
) {
    if (answer != null) {
        ChoiceItemRevealed(
            choice = choice,
            index = index,
            isSelected = answer.selectedChoiceIndex == index,
        )
    } else {
        ChoiceItemDefault(
            choice = choice,
            index = index,
            onClick = onClick,
        )
    }
}

@Composable
private fun ChoiceItemDefault(
    choice: Choice,
    index: Int,
    onClick: () -> Unit,
) {
    val backgroundColor = when (index) {
        0 -> Red2
        1 -> Blue2
        2 -> Yellow3
        3 -> Green2
        else -> Color.Gray
    }

    // TODO Add icons
    val icon = when (index) {
        0 -> DesignR.drawable.ic_triangle
        1 -> DesignR.drawable.ic_diamond
        2 -> DesignR.drawable.ic_circle
        else -> DesignR.drawable.ic_square
    }
    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(4.dp))
            .height(100.dp)
            .clickable(
                onClick = onClick,
            ),
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(32.dp),
        )
        Text(
            text = choice.answer ?: "",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = contrastiveTo(backgroundColor),
        )
    }
}

@Composable
private fun ChoiceItemRevealed(
    choice: Choice,
    index: Int,
    isSelected: Boolean,
) {
    val backgroundColor = when {
        isSelected && !choice.correct -> Red
        choice.correct -> Green
        else -> Pink
    }

    val icon = if (choice.correct) {
        DesignR.drawable.ic_correct
    } else {
        DesignR.drawable.ic_wrong
    }

    val alignment = if (index % 2 == 0) {
        Alignment.TopStart
    } else {
        Alignment.TopEnd
    }

    Box(
        modifier = Modifier
            .background(backgroundColor, shape = RoundedCornerShape(4.dp))
            .height(100.dp),
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .align(alignment)
                .offset(
                    x = if (alignment == Alignment.TopStart) (-8).dp else (8).dp,
                    (-8).dp,
                ),
        )
        Text(
            text = choice.answer ?: "",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            color = contrastiveTo(backgroundColor),
        )
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
            uiState = QuizUiState(
                currentQuestion = sampleQuestion,
            ),
            onSelect = {},
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
            uiState = QuizUiState(
                currentQuestion = sampleQuestion,
                answer = AnswerUiState(
                    selectedChoiceIndex = 1,
                ),
            ),
            onSelect = {},
        )
    }
}
