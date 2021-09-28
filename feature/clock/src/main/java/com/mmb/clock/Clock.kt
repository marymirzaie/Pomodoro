package com.mmb.clock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.component.pom.ControlButton
import com.mmb.ui_compose.component.pom.PomodoroClock
import com.mmb.ui_compose.component.pom.entity.ControlState
import com.mmb.ui_compose.component.pom.entity.PomodoroScreenEntity

@Composable
fun Clock(
    navigateUp: () -> Unit,
) {
    Clock(
        viewModel = viewModel()
    )
}

@Composable
internal fun Clock(
    viewModel: PomodoroClockViewModel,
) {
    val timerState = viewModel.timer.observeAsState()
    val state = viewModel.buttonState.observeAsState()
    PomScreen(
        PomodoroScreenEntity(
            text = timerState.value?.toString() ?: "",
            numberOfPoms = 3,
            pomsCompleted = 2,
            state = state.value ?: ControlState.Paused,
            onControlButtonClicked = viewModel::onButtonClicked
        )
    )
}

@Composable
fun PomScreen(
    entity: PomodoroScreenEntity,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        PomodoroClock(
            text = entity.text,
            numberOfPoms = entity.numberOfPoms,
            pomsCompleted = entity.pomsCompleted,
        )
        Spacer(modifier = Modifier
            .height(Layout.bodyMargin)
            .fillMaxWidth())
        ControlButton(
            state = entity.state,
            onClick = entity.onControlButtonClicked,
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
        state = ControlState.Running
    ) {}
    )
}
