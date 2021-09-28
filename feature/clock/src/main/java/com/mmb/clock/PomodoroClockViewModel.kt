package com.mmb.clock

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.clock.TimerState.Companion.SECOND_MILLS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PomodoroClockViewModel @Inject constructor() : ViewModel() {

    private val _progress: MutableLiveData<Float> = MutableLiveData()
    val progress: LiveData<Float> = _progress

    private val _timer: MutableLiveData<TimerState> = MutableLiveData()
    val timer: LiveData<TimerState> = _timer

    private var currentTimerState = TimerState(0, 0)
    private val sessionTimerState = TimerState(3, 0)

    private var countDownTimer: CountDownTimer? = null

    fun subscribe() {
        currentTimerState = sessionTimerState
    }

    fun startTimer() {
        if (countDownTimer != null) return
        countDownTimer = object : CountDownTimer(currentTimerState.convertToSeconds(),
            SECOND_MILLS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerSate(millisUntilFinished.convertToTimerState())
            }

            override fun onFinish() {
                updateTimerSate(sessionTimerState.copy(finish = true))
            }
        }.start()
    }

    fun pauseTimer() {
        _timer.value?.apply {
            currentTimerState = TimerState(
                minutes = minutes,
                seconds = seconds
            )
        }
        countDownTimer?.cancel()
        countDownTimer = null
    }

    fun onButtonClicked() {
        if (countDownTimer == null) {
            startTimer()
        } else {
            pauseTimer()
        }
    }

    private fun updateProgress(timerState: TimerState): Float {
        val total = sessionTimerState.convertToSeconds()
        val remaining = total - timerState.convertToSeconds()
        return remaining.toFloat() * 360 / total.toFloat()
    }

    private fun updateTimerSate(state: TimerState) {
        viewModelScope.launch {
            _timer.value = state
            _progress.value = updateProgress(state)
        }
    }
}