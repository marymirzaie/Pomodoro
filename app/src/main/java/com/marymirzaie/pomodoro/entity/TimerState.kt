package com.marymirzaie.pomodoro.entity

data class TimerState(
    val minutes: Long = 0,
    val seconds: Long = 0,
    val isCompleted: Boolean = false,
)