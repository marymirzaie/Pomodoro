package com.marymirzaie.pomodoro.pom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marymirzaie.pomodoro.entity.TimerState
import com.marymirzaie.pomodoro.pom.TimerJob.Companion.SECONDS_PER_MINUTE
import kotlinx.coroutines.flow.StateFlow

class PomodoroViewModel : ViewModel() {

    private val _timer = TimerJob(viewModelScope)
    val timer: StateFlow<TimerState> = _timer.timerStateFlow
    val progress: StateFlow<Float> = _timer.progressStateFlow

    var seconds: Long = 0L
    var minutes: Long = 0L
    var totalSeconds: Long = 0L

    fun startTimer() {
        //todo write tests for timer
        totalSeconds = seconds + minutes * SECONDS_PER_MINUTE
        _timer.startTimer(totalMinutes = minutes, totalSeconds = seconds)
    }

    fun pauseTimer() {
        _timer.cancelTimer()
    }
}