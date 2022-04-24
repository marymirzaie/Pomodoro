package com.mmb.clock.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.clock.entity.SessionState
import com.mmb.clock.entity.SessionState.*
import com.mmb.clock.entity.TimerState
import com.mmb.core.SettingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PomodoroClockViewModel @Inject constructor(
    private val repository: SettingRepository,
    private val timeManager: TimeManager
) : ViewModel() {

    private val _buttonState: MutableLiveData<Boolean> = MutableLiveData()
    val timerRunning: LiveData<Boolean> = _buttonState

    private val _sessionType: MutableLiveData<SessionState> = MutableLiveData()
    val sessionType: LiveData<SessionState> = _sessionType

    val sessionName = repository.getSessionName()
    private var sessionTimerState = TimerState()

    val progress: LiveData<Float> = timeManager.progress
    val timer: LiveData<TimerState> = timeManager.timer

    private var currentSessionType: SessionState = FOCUS

    init {
        viewModelScope.launch {
            repository.getFocusDuration().collect { duration ->
                sessionTimerState = TimerState(duration.toLong(), 0)
                timeManager.initiate(sessionTimerState)
            }
        }
    }

    fun startTimer() {
        _buttonState.value = true
        timeManager.startTimer(sessionTimerState, this::onSessionCompleted)
    }

    fun pauseTimer() {
        _buttonState.value = false
        timeManager.pauseTimer()
    }

    fun restartTimer() {
        _buttonState.value = false
        timeManager.restartTimer(sessionTimerState)
    }

    private fun onSessionCompleted() {
        val next = getNextSession()
        _buttonState.value = false
        currentSessionType = next
        _sessionType.value = next
    }

    private fun getNextSession(): SessionState {
        return when (currentSessionType) {
            //handle short and long breaks
            FOCUS -> SHORT_BREAK
            SHORT_BREAK -> FOCUS
            LONG_BREAK -> FOCUS
        }
    }
}