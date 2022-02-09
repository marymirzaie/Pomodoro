package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.clickable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
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
        Icons.Filled.Pause
    } else {
        Icons.Filled.PlayArrow
    }
    val contentDescription = if (state is ControlState.Running) {
        stringResource(id = R.string.stop_timer_description)
    } else {
        stringResource(id = R.string.resume_timer_description)
    }
    Icon(
        imageVector = vectorImage,
        tint = MaterialTheme.colors.primary,
        contentDescription = contentDescription,
        modifier = modifier.clickable { onClick.invoke() },
    )
}

@Preview
@Composable
fun PomodoroButtonPreview() {
    ControlButton(state = ControlState.Running) {

    }
}