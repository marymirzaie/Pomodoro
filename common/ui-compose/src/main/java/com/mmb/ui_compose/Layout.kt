package com.mmb.ui_compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object Layout {

    val normalFontSize = 18.sp

    val iconSize: Dp
        @Composable get() = 32.dp

    val largeMargin: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 32.dp
            in 600..904 -> 32.dp
            in 905..1239 -> 0.dp
            in 1240..1439 -> 200.dp
            else -> 0.dp
        }

    val bodyMargin: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 16.dp
            in 600..904 -> 32.dp
            in 905..1239 -> 0.dp
            in 1240..1439 -> 200.dp
            else -> 0.dp
        }

    val gutter: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 8.dp
            in 600..904 -> 16.dp
            in 905..1239 -> 16.dp
            in 1240..1439 -> 32.dp
            else -> 32.dp
        }

    val stroke: Dp
        @Composable get() = when (LocalConfiguration.current.screenWidthDp) {
            in 0..599 -> 2.dp
            in 600..904 -> 4.dp
            in 905..1239 -> 4.dp
            in 1240..1439 -> 8.dp
            else -> 8.dp
        }
}