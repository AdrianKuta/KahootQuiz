package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.adriankuta.kahootquiz.core.designsystem.Purple

@Composable
fun TimerBar(
    totalSeconds: Int,
    remainingSeconds: Int,
    modifier: Modifier = Modifier,
) {
    val target =
        if (totalSeconds <= 0) 0f else (remainingSeconds.toFloat() / totalSeconds).coerceIn(0f, 1f)
    val progress: Float by animateFloatAsState(
        targetValue = target,
        label = "Timer",
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
    )

    Box(
        modifier = modifier
            .fillMaxWidth(progress.coerceIn(0f, 1f))
            .background(
                color = Purple,
                shape = RoundedCornerShape(percent = 50),
            ),
    ) {
        Text(
            text = "$remainingSeconds",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 8.dp),
            color = Color.White,
        )
    }
}
