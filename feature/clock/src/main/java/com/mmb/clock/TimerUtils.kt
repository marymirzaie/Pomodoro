package com.mmb.clock

import com.mmb.clock.TimerState.Companion.SECONDS_PER_MINUTE

fun Long.convertToTimerState(): TimerState {
    val min: Long = this / SECONDS_PER_MINUTE
    val remaining = this - min * SECONDS_PER_MINUTE
    return TimerState(
        minutes = min,
        seconds = remaining / TimerState.SECOND_MILLS
    )
}