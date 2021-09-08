package com.marymirzaie.pomodoro.pom

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.marymirzaie.pomodoro.pom.entity.TimerState

@Composable
//the final view
fun Pomodoro() {

}

@Composable
//a clock that goes through by time
fun PomodoroClock(timerState: TimerState) {
    Row {
        Text(text = timerState.hours.toString())
        Text(text = ":")
        Text(text = timerState.minutes.toString())
    }
}

@Composable
//consists of 3 pomodoro icons that shows how many times
fun PomodoroCount() {

}

@Composable
//consists of pause and resume buttons
fun PomodoroControl() {

}