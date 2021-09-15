package com.marymirzaie.pomodoro.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marymirzaie.pomodoro.entity.TimerState

@Composable
//the final view
fun Pomodoro(timerState: TimerState, progress: Float, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(4.dp)
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        PomodoroClock(timerState)
        PomodoroProgress(progress, modifier.align(Alignment.Center))
    }
}

@Composable
fun PomodoroProgress(
    progress: Float,
    modifier: Modifier = Modifier,
) {
    val stroke = with(LocalDensity.current) { Stroke(10.dp.toPx()) }
    val color = MaterialTheme.colors.primary
    Canvas(modifier = modifier
        .fillMaxSize()
        .aspectRatio(1f)
        .padding(1.dp)
        .clip(CircleShape)) {
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
    Pomodoro(timerState = TimerState(minutes = 10, seconds = 5), 500f)
}