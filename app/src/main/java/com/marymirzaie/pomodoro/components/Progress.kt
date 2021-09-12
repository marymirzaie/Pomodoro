package com.marymirzaie.pomodoro.ui.pomodoro

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marymirzaie.pomodoro.pom.entity.TimerState

@Composable
//the final view
fun Pomodoro(timerState: TimerState, progress: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .size(250.dp)
        .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        PomodoroProgress(progress,
            modifier
                .padding(4.dp)
                .matchParentSize()
                .align(Alignment.Center)
                .fillMaxWidth())
        PomodoroClock(timerState)
    }
}

@Composable
fun PomodoroProgress(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val stroke = with(LocalDensity.current) { Stroke(4.dp.toPx()) }
    val color = MaterialTheme.colors.primary
    Canvas(modifier = modifier) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.height - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = progress,
            topLeft = topLeft,
            size = size,
            useCenter = false,
            style = stroke
        )
    }
}

@Composable
@Preview
fun PomodoroPreview() {
    Pomodoro(timerState = TimerState(minutes = 10, seconds = 5), 100f)
}