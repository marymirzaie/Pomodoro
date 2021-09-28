package com.marymirzaie.pomodoro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mmb.navigation.PomodoroAppNavigation
import com.mmb.ui_compose.theme.PomodoroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroTheme {
                PomodoroAppNavigation()

            }
        }
    }
}
