package com.marymirzaie.pomodoro.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

//todo fix dark theme
private val DarkColorPalette = darkColors(
    primary = Purple700,
    onPrimary = Purple500,
    secondary = Grey600,
    onSecondary = Grey400,
    primaryVariant = OceanBlue,
    secondaryVariant = Grey200
)

private val LightColorPalette = lightColors(
    primary = Purple200,
    onPrimary = Purple500,
    secondary = Grey100,
    onSecondary = Grey400,
    primaryVariant = OceanBlue,
    secondaryVariant = Grey200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
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