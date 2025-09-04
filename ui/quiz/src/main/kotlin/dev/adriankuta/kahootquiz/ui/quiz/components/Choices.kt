@file:OptIn(ExperimentalLayoutApi::class)

package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.adriankuta.kahootquiz.core.designsystem.Blue2
import dev.adriankuta.kahootquiz.core.designsystem.Green
import dev.adriankuta.kahootquiz.core.designsystem.Green2
import dev.adriankuta.kahootquiz.core.designsystem.Pink
import dev.adriankuta.kahootquiz.core.designsystem.Red
import dev.adriankuta.kahootquiz.core.designsystem.Red2
import dev.adriankuta.kahootquiz.core.designsystem.Yellow3
import dev.adriankuta.kahootquiz.core.designsystem.contrastiveTo
import dev.adriankuta.kahootquiz.domain.models.Choice
import dev.adriankuta.kahootquiz.core.designsystem.R as DesignR

@Composable
fun Choices(
    choices: List<Choice>,
    onSelect: (Int) -> Unit,
    selectedChoiceIndex: Int?,
    modifier: Modifier = Modifier,
) {
    EvenGrid(
        items = choices,
        columns = 2,
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) { choice, index ->
        ChoiceItem(
            choice = choice,
            index = index,
            selectedChoiceIndex = selectedChoiceIndex,
            onClick = { onSelect(index) },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        )
    }
}

@Composable
private fun ChoiceItem(
    choice: Choice,
    onClick: () -> Unit,
    index: Int,
    selectedChoiceIndex: Int?,
    modifier: Modifier = Modifier,
) {
    if (selectedChoiceIndex != null) {
        ChoiceItemRevealed(
            choice = choice,
            index = index,
            isSelected = selectedChoiceIndex == index,
            modifier = modifier,
        )
    } else {
        ChoiceItemDefault(
            choice = choice,
            index = index,
            onClick = onClick,
            modifier = modifier,
        )
    }
}

@Composable
private fun ChoiceItemDefault(
    choice: Choice,
    index: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val backgroundColor = when (index) {
        0 -> Red2
        1 -> Blue2
        2 -> Yellow3
        3 -> Green2
        else -> Color.Gray
    }

    val icon = when (index) {
        0 -> DesignR.drawable.ic_triangle
        1 -> DesignR.drawable.ic_diamond
        2 -> DesignR.drawable.ic_circle
        else -> DesignR.drawable.ic_square
    }
    Box(
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(4.dp))
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
    modifier: Modifier = Modifier,
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
        modifier = modifier
            .background(backgroundColor, shape = RoundedCornerShape(4.dp)),
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            modifier = Modifier
                .align(alignment)
                .offset(
                    x = if (alignment == Alignment.TopStart) (-8).dp else (8).dp,
                    y = (-8).dp,
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
