package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.Layout

@Composable
//a clock that goes through by time
fun PomodoroClock(
    text: String,
    numberOfPoms: Int,
    pomsCompleted: Int,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    PomodoroProgress(progress = progress, modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(Layout.largeMargin)) {
            Text(
                text = text,
                style = MaterialTheme.typography.h3,
                modifier = modifier.wrapContentSize(),
                color = MaterialTheme.colors.primary
            )
            PomodoroCount(
                numberOfPoms = numberOfPoms,
                pomsCompleted = pomsCompleted
            )
        }
    }
}

@Preview
@Composable
fun PomodoroClockPreview() {
    PomodoroClock("12:00", 2, 3, 100f)
}