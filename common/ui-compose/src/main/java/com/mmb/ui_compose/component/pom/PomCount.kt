package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.Layout

@Composable
//consists of #pomodoros that indicates session count
fun PomodoroCount(
    numberOfPoms: Int,
    pomsCompleted: Int,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .wrapContentSize()
            .padding(Layout.gutter),
    ) {
        for (index in 0 until pomsCompleted) {
            Icon(imageVector = Icons.Filled.Circle,
                contentDescription = null,
                modifier = Modifier
                    .padding(Layout.stroke))
        }
        for (index in pomsCompleted until numberOfPoms) {
            Icon(imageVector = Icons.Filled.RadioButtonUnchecked,
                contentDescription = null,
                modifier = Modifier.padding(Layout.stroke))
        }
    }
}

@Composable
@Preview
fun PomodoroCountPreview() {
    PomodoroCount(5, 2)
}