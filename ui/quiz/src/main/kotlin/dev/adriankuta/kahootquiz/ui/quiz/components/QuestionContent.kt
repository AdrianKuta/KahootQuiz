package dev.adriankuta.kahootquiz.ui.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import coil3.compose.AsyncImage
import dev.adriankuta.kahootquiz.core.designsystem.toAnnotatedString
import dev.adriankuta.kahootquiz.domain.models.Question

@Composable
fun QuestionContent(
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
                .weight(1f)
                .clip(shape = RoundedCornerShape(4.dp)),
        )
        Spacer(Modifier.height(16.dp))
        val questionText = androidx.compose.runtime.remember(question.question) {
            HtmlCompat.fromHtml(
                question.question ?: "",
                HtmlCompat.FROM_HTML_MODE_COMPACT,
            ).toAnnotatedString()
        }
        Text(
            text = questionText,
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
