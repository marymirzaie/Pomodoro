package com.marymirzaie.pomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marymirzaie.pomodoro.R

@Composable
fun PomodoroButton(modifier: Modifier = Modifier, onClick: () -> Unit = {}) {
    IconButton(modifier = modifier
        .wrapContentSize(align = Alignment.Center)
        .padding(4.dp)
        .clip(CircleShape)
        .background(MaterialTheme.colors.primary),
        onClick = onClick
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(painter = painterResource(id = R.drawable.ic_play_button),
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = Color.White)
        }
    }
}

@Preview
@Composable
fun PomodoroButtonPreview() {
    PomodoroButton()
}