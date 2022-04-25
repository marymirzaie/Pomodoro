package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout.iconSize
import com.mmb.ui_compose.R

@Composable
fun ControlButton(
    modifier: Modifier = Modifier,
    running: Boolean,
    onResumeClicked: () -> Unit,
    onRestartClicked: () -> Unit,
    onPauseClicked: () -> Unit,
) {
    if (running) {
        Row {
            Icon(
                imageVector = Icons.Filled.Pause,
                tint = MaterialTheme.colors.primary,
                contentDescription = stringResource(id = R.string.stop_timer_description),
                modifier = modifier
                    .size(iconSize)
                    .clickable { onPauseClicked.invoke() },
            )
            Spacer(modifier = Modifier.width(width = 16.dp))
            Icon(
                imageVector = Icons.Outlined.Refresh,
                tint = MaterialTheme.colors.primary,
                contentDescription = stringResource(id = R.string.restart_timer_description),
                modifier = modifier
                    .size(iconSize)
                    .clickable { onRestartClicked.invoke() },
            )
        }
    } else {
        Icon(
            imageVector = Icons.Filled.PlayArrow,
            tint = MaterialTheme.colors.primary,
            contentDescription = stringResource(id = R.string.resume_timer_description),
            modifier = modifier
                .size(iconSize)
                .clickable { onResumeClicked.invoke() },
        )
    }
}

@Preview
@Composable
fun PomodoroButtonPreview() {
    ControlButton(running = true, onResumeClicked = {}, onRestartClicked = {},
        onPauseClicked = {})
}