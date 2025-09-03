package dev.adriankuta.kahootquiz.core.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance

@Composable
fun contrastiveTo(color: Color): Color = if (color.luminance() < 0.5) {
    Color.White
} else {
    Color.Black
}

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Grey = Color(0xFFFAFAFA)
val Pink = Color(0xFFFF99AA)
val Red = Color(0xFFFF3355)
val Red2 = Color(0xFFE21B3C)
val Blue2 = Color(0xFF1368CE)
val Yellow3 = Color(0xFFD89E00)
val Green = Color(0xFF66BF39)
val Green2 = Color(0xFF26890C)
