package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.component.pom.entity.ControlState
import com.mmb.ui_compose.component.pom.entity.PomodoroScreenEntity

@Composable
fun PomScreen(
    entity: PomodoroScreenEntity,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        PomodoroClock(
            text = entity.text,
            numberOfPoms = entity.numberOfPoms,
            pomsCompleted = entity.pomsCompleted,
        )
        Spacer(modifier = Modifier
            .height(Layout.bodyMargin)
            .fillMaxWidth())
        ControlButton(state = entity.state, onClick = entity.onControlButtonClicked)
    }
}

@Preview
@Composable
fun PomScreenPreview() {
    PomScreen(PomodoroScreenEntity(
        "12:00",
        3,
        2,
        state = ControlState.Running
    ) {}
    )
}


