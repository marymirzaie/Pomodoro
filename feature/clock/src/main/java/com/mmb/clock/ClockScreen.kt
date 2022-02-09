package com.mmb.clock

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.component.pom.ControlButton
import com.mmb.ui_compose.component.pom.PomodoroClock
import com.mmb.ui_compose.component.pom.entity.ControlState
import com.mmb.ui_compose.component.pom.entity.PomodoroScreenEntity

@Composable
fun Clock(navigateUp: () -> Unit) {
    val viewModel = hiltViewModel<PomodoroClockViewModel>()
    Clock(viewModel = viewModel)
}

@Composable
internal fun Clock(
    viewModel: PomodoroClockViewModel,
) {

    val timerState = viewModel.timer.observeAsState()
    val state = viewModel.buttonState.observeAsState()
    val progress = viewModel.progress.observeAsState()
    PomScreen(
        PomodoroScreenEntity(
            text = timerState.value?.toString() ?: "",
            numberOfPoms = 3,
            pomsCompleted = 2,
            state = state.value ?: ControlState.Paused,
            progress = progress.value ?: 0f,
            onControlButtonClicked = viewModel::onButtonClicked
        ),
    )
}

@Composable
fun PomScreen(
    entity: PomodoroScreenEntity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            PomodoroClock(
                text = entity.text,
                progress = entity.progress,
                modifier = Modifier
                    .padding(horizontal = Layout.largeMargin)
                    .aspectRatio(1f)
            )
        }
        Spacer(
            modifier = Modifier
                .height(Layout.largeMargin)
                .fillMaxWidth()
        )
        ControlButton(
            state = entity.state,
            onResumeClicked = entity.onControlButtonClicked,
            onRestartClicked = {},
            onPauseClicked = entity.onControlButtonClicked,
            modifier = Modifier.size(50.dp)
        )
    }
}

@Composable
@Preview
fun PomScreenPreview() {
    PomScreen(PomodoroScreenEntity(
        "12:00",
        3,
        2,
        state = ControlState.Running,
        progress = 100f
    ) {}
    )
}
