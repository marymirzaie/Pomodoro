package com.mmb.session.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Computer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout

@Composable
fun SessionIcon(
    title: String,
    imageVector: ImageVector,
    duration: String,
    tint: Color,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onSurface,
) {
    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = duration, fontSize = Layout.tinyFont, color = textColor)
        Icon(
            imageVector = imageVector,
            tint = tint,
            contentDescription = "",
            modifier = modifier.size(25.dp)
        )
        Text(text = title, fontSize = Layout.tinyFont, color = textColor)
    }
}

@Composable
@Preview
fun SessionIconPreview() {
    SessionIcon("Focus", Icons.Sharp.Computer, "25 Min", MaterialTheme.colors.primary)
}
