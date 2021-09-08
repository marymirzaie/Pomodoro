package com.marymirzaie.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import com.marymirzaie.pomodoro.pom.PomodoroClock
import com.marymirzaie.pomodoro.pom.PomodoroViewModel
import com.marymirzaie.pomodoro.ui.theme.PomodoroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTheme {
                val viewModel = PomodoroViewModel()
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val timerState = viewModel.timer.collectAsState()
                    PomodoroClock(timerState = timerState.value)
                }
                viewModel.startTimer(0, 3)
            }
        }
    }
}
