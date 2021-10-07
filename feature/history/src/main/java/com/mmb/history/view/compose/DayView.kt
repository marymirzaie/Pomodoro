package com.mmb.history.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout

@Composable
fun DayView(
    date: String,
    value: Int,
    maxValue: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(Layout.bodyMargin)
            .clip(shape = CircleShape)
            .background(color = MaterialTheme.colors.surface),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value.toString(),
            color = MaterialTheme.colors.secondary)
        Box(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(shape = CircleShape)
            .background(MaterialTheme.colors.primary)
        )
    }
}

@Preview
@Composable
fun DayViewPreview() {
    DayView(
        "1",
        2,
        3,
        Modifier.height(300.dp)
    )
}