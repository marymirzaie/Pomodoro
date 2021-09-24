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

@Composable
fun PomScreen(
    text: String,
    numberOfPoms: Int,
    pomsCompleted: Int,
    state: ControlState,
    modifier: Modifier = Modifier,
    onControlButtonClicked: () -> Unit,
) {
    Column(modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        PomodoroClock(
            text = text,
            numberOfPoms = numberOfPoms,
            pomsCompleted = pomsCompleted,
        )
        Spacer(modifier = Modifier
            .height(Layout.bodyMargin)
            .fillMaxWidth())
        ControlButton(state = state, onClick = onControlButtonClicked)
    }
}

@Preview
@Composable
fun PomScreenPreview() {
    PomScreen(
        "12:00",
        3,
        2,
        state = ControlState.Running
    ) {}
}


