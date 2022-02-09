package com.mmb.ui_compose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Pink800,
    onPrimary = Color.Black,
    secondary = Pink100,
    onSecondary = Pink200,
    surface = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Pink800,
    onPrimary = Color.Black,
    secondary = Pink100,
    onSecondary = Pink200,
    surface = Grey800,
)

@Composable
fun PomodoroTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        typography = Typography,
        content = content,
    )
}