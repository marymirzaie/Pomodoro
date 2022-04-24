package com.mmb.clock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mmb.clock.entity.TimerState
import com.mmb.core.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PomodoroClockViewModel @Inject constructor(
    private val repository: SettingRepository,
    private val timeManager: TimeManager
) : ViewModel() {

    private val _buttonState: MutableLiveData<Boolean> = MutableLiveData()
    val timerRunning: LiveData<Boolean> = _buttonState

    val sessionName = repository.getSessionName()
    private var sessionTimerState = TimerState()

    val progress: LiveData<Float> = timeManager.progress
    val timer: LiveData<TimerState> = timeManager.timer

    fun subscribe(duration: Int) {
        sessionTimerState = TimerState(duration.toLong(), 0)
        timeManager.initiate(sessionTimerState)
    }

    fun startTimer(onTimerCompleted: () -> Unit) {
        _buttonState.value = true
        timeManager.startTimer(sessionTimerState, onTimerCompleted)
    }

    fun pauseTimer() {
        _buttonState.value = false
        timeManager.pauseTimer()
    }

    fun restartTimer() {
        _buttonState.value = false
        timeManager.restartTimer(sessionTimerState)
    }

}