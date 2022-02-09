package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
//a clock that goes through by time
fun PomodoroClock(
    text: String,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    PomodoroProgress(percentage = progress, modifier = modifier) {
        Text(
            text = text,
            style = MaterialTheme.typography.h3,
            modifier = modifier
                .padding(horizontal = 20.dp)
                .wrapContentSize(),
            color = Color.Black
        )
    }
}

@Preview
@Composable
fun PomodoroClockPreview() {
    PomodoroClock("12:00", 100f)
}