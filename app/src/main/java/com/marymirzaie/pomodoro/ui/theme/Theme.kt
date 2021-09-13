package com.marymirzaie.pomodoro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Red200,
    onPrimary = Pink200,
    secondary = Grey200,
    onSecondary = Grey100,
    surface = Color.White
)

private val DarkColorPalette = darkColors(
    primary = Red200,
    onPrimary = Pink800,
    secondary = Grey100,
    onSecondary = Grey200,
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
        typography = PomodoroTypogeraphy,
        shapes = Shapes,
        content = content,
    )
}