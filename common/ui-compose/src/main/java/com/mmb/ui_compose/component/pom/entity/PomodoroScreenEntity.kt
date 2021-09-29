package com.mmb.ui_compose.component.pom.entity

data class PomodoroScreenEntity(
    val text: String,
    val numberOfPoms: Int,
    val pomsCompleted: Int,
    val progress: Float,
    val state: ControlState,
    val onControlButtonClicked: () -> Unit,
)