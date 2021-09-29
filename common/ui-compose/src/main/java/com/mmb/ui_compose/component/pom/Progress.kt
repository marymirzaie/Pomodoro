package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout

@Composable
fun PomodoroProgress(
    progress: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val stroke = with(LocalDensity.current) { Stroke(Layout.gutter.toPx()) }
    val color = MaterialTheme.colors.primary
    Box(modifier = modifier
        .padding(Layout.largeMargin)
        .wrapContentSize()
        .aspectRatio(1f)
        .shadow(elevation = 12.dp, shape = CircleShape)
        .clip(CircleShape)
        .background(MaterialTheme.colors.surface, shape = CircleShape)
        .padding(Layout.stroke),
        contentAlignment = Alignment.Center) {
        Canvas(modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .clip(CircleShape)
            .padding(Layout.gutter)) {
            val innerRadius = (size.minDimension - stroke.width) / 2
            val halfSize = size / 2.0f
            val topLeft = Offset(
                halfSize.height - innerRadius,
                halfSize.height - innerRadius
            )
            val size = Size(innerRadius * 2, innerRadius * 2)
            drawArc(
                color = color,
                startAngle = 270f,
                sweepAngle = progress,
                topLeft = topLeft,
                size = size,
                useCenter = false,
                style = stroke
            )
        }
        content()
    }
}