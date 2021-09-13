package com.marymirzaie.pomodoro.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marymirzaie.pomodoro.components.PomodoroButton
import com.marymirzaie.pomodoro.entity.TimerState
import com.marymirzaie.pomodoro.ui.pomodoro.Pomodoro

@Composable
fun PomodoroScreen(timerState: TimerState, progress: Float, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(40.dp)) {
        Pomodoro(timerState = timerState,
            progress,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f))
        Spacer(modifier = Modifier.size(32.dp))
        PomodoroButton()
    }
}

@Preview
@Composable
fun PomodoroScreenPreview() {
    PomodoroScreen(TimerState(3, 15), 200f)
}