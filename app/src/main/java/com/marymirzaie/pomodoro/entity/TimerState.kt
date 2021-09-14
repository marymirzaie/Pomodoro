package com.marymirzaie.pomodoro.entity

class TimerState(
    val minutes: Long = 0,
    val seconds: Long = 0,
    val finish: Boolean = false,
)