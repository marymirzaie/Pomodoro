package com.mmb.ui_compose.component.pom

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.R
import com.mmb.ui_compose.component.pom.entity.ControlState

@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    state: ControlState,
    onClick: () -> Unit,
) {
    val vectorImage = if (state is ControlState.Running) {
        Icons.Filled.PauseCircle
    } else {
        Icons.Filled.PlayCircle
    }
    val contentDescription = if (state is ControlState.Running) {
        stringResource(id = R.string.stop_timer_description)
    } else {
        stringResource(id = R.string.resume_timer_description)
    }
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            imageVector = vectorImage,
            contentDescription = contentDescription
        )
    }
}

@Preview
@Composable
fun PomodoroButtonPreview() {
    ControlButton(state = ControlState.Running) {

    }
}