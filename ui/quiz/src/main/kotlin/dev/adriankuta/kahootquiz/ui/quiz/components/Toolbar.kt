package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.adriankuta.kahootquiz.core.designsystem.Grey
import dev.adriankuta.kahootquiz.ui.quiz.R
import dev.adriankuta.kahootquiz.core.designsystem.R as DesignR

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    currentQuestionIndex: Int = 0,
    totalQuestions: Int = 0,
) {
    Box(
        modifier = modifier,
    ) {
        Text(
            text = "${currentQuestionIndex + 1}/$totalQuestions",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(percent = 50),
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        )

        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .background(
                    color = Grey,
                    shape = RoundedCornerShape(percent = 50),
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
        ) {
            Image(
                painter = painterResource(id = DesignR.drawable.ic_type),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = stringResource(R.string.quiz),
            )
        }
    }
}
