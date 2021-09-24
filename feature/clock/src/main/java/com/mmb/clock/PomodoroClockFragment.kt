package com.mmb.clock

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mmb.ui_compose.component.pom.PomScreen
import com.mmb.ui_compose.component.pom.entity.ControlState
import com.mmb.ui_compose.component.pom.entity.PomodoroScreenEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PomodoroClockFragment : Fragment() {
    val viewModel: PomodoroClockViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val timerState = viewModel.timer.observeAsState()
                val state = if (timerState.value?.finish == true) {
                    ControlState.Paused
                } else {
                    ControlState.Running
                }
                PomScreen(
                    PomodoroScreenEntity(
                        text = timerState.value?.toString() ?: "",
                        numberOfPoms = 3,
                        pomsCompleted = 2,
                        state = state
                    ) {
                        if (state == ControlState.Running) {
                            viewModel.pauseTimer()
                        } else {
                            viewModel.startTimer()
                        }
                    }
                )
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}