package com.mmb.ui_compose.component.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.sharp.Computer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.R

enum class SessionState {
    FOCUS,
    SHORT_BREAK,
    LONG_BREAK
}

@Composable
fun SessionProgress(state: SessionState, modifier: Modifier = Modifier) {
    val active = MaterialTheme.colors.primary
    val inactive = MaterialTheme.colors.primaryVariant

    val activeTextColor = MaterialTheme.colors.onSurface
    val inactiveTextColor = MaterialTheme.colors.onSecondary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Layout.bodyMargin),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        SessionIcon(
            title = stringResource(id = R.string.session_focus),
            imageVector = Icons.Sharp.Computer,
            duration = "25 Min",
            tint = if (state == SessionState.FOCUS) active else inactive,
            textColor = if (state == SessionState.FOCUS) activeTextColor else inactiveTextColor,
        )
        SessionIcon(
            title = stringResource(id = R.string.session_short_break),
            imageVector = Icons.Filled.LocalCafe,
            duration = "5 Min",
            tint = if (state == SessionState.SHORT_BREAK) active else inactive,
            textColor = if (state == SessionState.SHORT_BREAK) activeTextColor else inactiveTextColor,
        )
        SessionIcon(
            title = stringResource(id = R.string.session_long_break),
            imageVector = Icons.Filled.Chair,
            duration = "15 Min",
            tint = if (state == SessionState.LONG_BREAK) active else inactive,
            textColor = if (state == SessionState.LONG_BREAK) activeTextColor else inactiveTextColor,
        )
    }
}

@Preview
@Composable
fun SessionPreview() {
    SessionProgress(state = SessionState.SHORT_BREAK)
}