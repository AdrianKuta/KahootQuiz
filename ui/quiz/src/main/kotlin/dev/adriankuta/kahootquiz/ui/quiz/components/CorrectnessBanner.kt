package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import dev.adriankuta.kahootquiz.core.designsystem.Green
import dev.adriankuta.kahootquiz.core.designsystem.Red
import dev.adriankuta.kahootquiz.ui.quiz.R

@Composable
fun AnswerFeedbackBanner(
    isCorrect: Boolean,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .zIndex(10f),
        shadowElevation = 8.dp,
        color = if (isCorrect) Green else Red,
        contentColor = Color.White,
    ) {
        Box {
            Text(
                text = stringResource(if (isCorrect) R.string.correct else R.string.wrong),
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}
