package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProgressIndicator(percentage: Float, modifier: Modifier = Modifier) {
    val stroke = with(LocalDensity.current) { Stroke(4.dp.toPx(), cap = StrokeCap.Round) }
    val color = MaterialTheme.colors.primary
    val bac = MaterialTheme.colors.primaryVariant
    Canvas(
        modifier = modifier
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        val innerRadius = (size.minDimension - stroke.width) / 2
        val halfSize = size / 2.0f
        val topLeft = Offset(
            halfSize.height - innerRadius,
            halfSize.height - innerRadius
        )
        val size = Size(innerRadius * 2, innerRadius * 2)
        val sweepAngle = percentage * 360f
        drawArc(
            color = bac,
            startAngle = 0f,
            sweepAngle = 360f,
            topLeft = topLeft,
            size = size,
            useCenter = false,
            style = stroke
        )
        drawArc(
            color = color,
            startAngle = 270f,
            sweepAngle = sweepAngle,
            topLeft = topLeft,
            size = size,
            useCenter = false,
            style = stroke
        )

        val angleInDegrees = sweepAngle + 180
        val x = -(innerRadius * kotlin.math.sin(
            Math.toRadians(
                angleInDegrees.toDouble()
            )
        )).toFloat() + ((size.width + 4.dp.toPx()) / 2)
        val y = (innerRadius * kotlin.math.cos(
            Math.toRadians(angleInDegrees.toDouble())
        )).toFloat() + ((size.height + 4.dp.toPx()) / 2)

        if (percentage != 0f) {
            drawCircle(
                color = color,
                radius = 10f,
                center = Offset(x, y)
            )
        }
    }
}

@Composable
@Preview
fun ProgressIndicatorPreview() {
    ProgressIndicator(0.8f)
}