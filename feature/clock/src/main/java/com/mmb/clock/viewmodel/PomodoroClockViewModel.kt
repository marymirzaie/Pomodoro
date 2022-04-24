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

    private val _completedPom: MutableLiveData<Int> = MutableLiveData()
    val completedPom: LiveData<Int> = _completedPom
    val pomCount = repository.getFullPomCount()

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
        when (currentSessionType) {
            FOCUS -> {
                viewModelScope.launch {
                    pomCount.collect { count ->
                        val currentPomState = _completedPom.value ?: 1
                        if (currentPomState == count) {
                            updateSessionType(LONG_BREAK)
                        } else {
                            updateSessionType(SHORT_BREAK)
                        }
                    }
                }
            }
            LONG_BREAK -> {
                viewModelScope.launch {
                    pomCount.collect {
                        _completedPom.value = 1
                    }
                }
                updateSessionType(FOCUS)
                val currentPomState = _completedPom.value ?: 1
                _completedPom.value = currentPomState + 1
            }
            else -> {
                updateSessionType(FOCUS)
                val currentPomState = _completedPom.value ?: 1
                _completedPom.value = currentPomState + 1
            }
        }
        timeManager.pauseTimer()
        _buttonState.value = false
    }

    private fun updateSessionType(type: SessionState) {
        currentSessionType = type
        _sessionType.value = currentSessionType
        viewModelScope.launch {

            when (currentSessionType) {
                FOCUS -> {
                    repository.getFocusDuration().collect {
                        sessionTimerState = TimerState(it.toLong())
                        timeManager.initiate(sessionTimerState)
                    }
                }
                LONG_BREAK -> {
                    repository.getLongBreakDuration().collect {
                        sessionTimerState = TimerState(it.toLong())
                        timeManager.initiate(sessionTimerState)
                    }
                }
                else -> {
                    repository.getShortBreakDuration().collect {
                        sessionTimerState = TimerState(it.toLong())
                        timeManager.initiate(sessionTimerState)
                    }
                }
            }
        }
    }
}