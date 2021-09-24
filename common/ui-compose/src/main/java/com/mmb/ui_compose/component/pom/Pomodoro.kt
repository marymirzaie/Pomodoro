package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.component.base.PomText

@Composable
//a clock that goes through by time
fun PomodoroClock(
    text: String,
    numberOfPoms: Int,
    pomsCompleted: Int,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .aspectRatio(1f)
        .wrapContentSize()
        .padding(Layout.bodyMargin)
        .shadow(elevation = 12.dp, shape = CircleShape)
        .clip(CircleShape)
        .background(MaterialTheme.colors.surface, shape = CircleShape)
        .padding(Layout.bodyMargin),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(Layout.largeMargin)) {
            PomText(text)
            PomodoroCount(
                numberOfPoms = numberOfPoms,
                pomsCompleted = pomsCompleted
            )
        }
    }
}

@Preview
@Composable
fun PomodoroClockPreview() {
    PomodoroClock("12:00", 2, 3)
}