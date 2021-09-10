package com.marymirzaie.pomodoro.pom

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marymirzaie.pomodoro.pom.entity.TimerState
import kotlinx.coroutines.flow.StateFlow

class PomodoroViewModel : ViewModel() {

    private val _timer = TimerJob(viewModelScope)
    val timer: StateFlow<TimerState> = _timer.timerStateFlow

    fun startTimer(totalHours: Long, totalMinutes: Long) {
        //todo write tests for timer
        _timer.startTimer(totalHours, totalMinutes)
    }

    fun pauseTimer() {
        _timer.cancelTimer()
    }
}