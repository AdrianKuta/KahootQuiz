package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> EvenGrid(
    items: List<T>,
    columns: Int,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable RowScope.(item: T, index: Int) -> Unit,
) {
    val rows = (items.size + columns - 1) / columns // total rows needed

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
    ) {
        repeat(rows) { rowIndex ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = horizontalArrangement,
            ) {
                repeat(columns) { columnIndex ->
                    val itemIndex = rowIndex * columns + columnIndex
                    if (itemIndex < items.size) {
                        content(items[itemIndex], itemIndex)
                    }
                }
            }
        }
    }
}
