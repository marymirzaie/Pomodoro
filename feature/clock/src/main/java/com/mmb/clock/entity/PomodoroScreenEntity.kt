package com.mmb.clock.entity

data class PomodoroScreenEntity(
    val sessionName: String,
    val text: String,
    val numberOfPoms: Int,
    val pomsCompleted: Int,
    val progress: Float,
    val timerRunning: Boolean,
)