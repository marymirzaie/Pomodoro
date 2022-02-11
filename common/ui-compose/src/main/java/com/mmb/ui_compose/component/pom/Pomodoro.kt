package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout

@Composable
//a clock that goes through by time
fun PomodoroClock(
    text: String,
    progress: Float,
    modifier: Modifier = Modifier,
) {
    PomodoroProgress(percentage = progress, modifier = modifier.padding(Layout.largeMargin)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .padding(4.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.secondary),
            contentAlignment = Alignment.Center
        ) {
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
}

@Preview
@Composable
fun PomodoroClockPreview() {
    PomodoroClock("12:00", 100f)
}