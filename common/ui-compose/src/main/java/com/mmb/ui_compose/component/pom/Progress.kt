package com.mmb.ui_compose.component.pom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mmb.ui_compose.Layout

@Composable
fun PomodoroProgress(
    percentage: Float,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(Layout.largeMargin)
            .aspectRatio(1f)
            .clip(CircleShape)
            .background(MaterialTheme.colors.secondary),
        contentAlignment = Alignment.Center
    ) {
        ProgressIndicator(percentage)
        content()
    }
}