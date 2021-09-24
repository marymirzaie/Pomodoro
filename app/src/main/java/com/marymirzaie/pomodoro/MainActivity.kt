package com.marymirzaie.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContent {
        //            com.mmb.ui_compose.theme.PomodoroTheme {
        //                val viewModel = PomodoroViewModel()
        //                // A surface container using the 'background' color from the theme
        //                viewModel.subscribe()
        //                Surface(color = MaterialTheme.colors.background) {
        //                    val timerState = viewModel.timer.collectAsState()
        //                    val progressState = viewModel.progress.collectAsState()
        //                    PomodoroScreen(timerState = timerState.value, progressState.value) {
        //                        viewModel.onButtonClicked()
        //                    }
        //                }
        //            }
        //        }
    }
}
