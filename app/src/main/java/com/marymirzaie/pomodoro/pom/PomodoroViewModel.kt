package com.marymirzaie.pomodoro.pom

import android.os.CountDownTimer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marymirzaie.pomodoro.entity.TimerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PomodoroViewModel : ViewModel() {

    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress

    private val _timer: MutableStateFlow<TimerState> = MutableStateFlow(TimerState(0, 0))
    val timer: StateFlow<TimerState> = _timer

    private var totalSeconds: Long = 0L
    private var lastTimerState = TimerState(0, 0)
    private var pomTimerState = TimerState(3, 0)

    private var countDownTimer: CountDownTimer? = null

    companion object {
        const val SECOND_MILLS = 1000L
        const val SECONDS_PER_MINUTE = 60000L
    }

    fun subscribe() {
        totalSeconds = getPomDuration() * SECONDS_PER_MINUTE
        //todo set pom timer
    }

    private fun getPomDuration(): Int {
        //todo impl with data store
        return 3
    }

    private fun calculateMinuteAndSeconds(seconds: Long): Pair<Long, Long> {
        val min: Long = seconds / SECONDS_PER_MINUTE
        val remaining = seconds - min * SECONDS_PER_MINUTE
        return min to remaining / SECOND_MILLS
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(totalSeconds, SECOND_MILLS) {
            override fun onTick(millisUntilFinished: Long) {
                val time = calculateMinuteAndSeconds(millisUntilFinished)
                updateTimerSate(TimerState(minutes = time.first, seconds = time.second))
            }

            override fun onFinish() {
                updateTimerSate(TimerState(pomTimerState.minutes, pomTimerState.seconds, true))
            }
        }.start()
    }

    fun pauseTimer() {
        val state = timer.value
        lastTimerState = TimerState(minutes = state.minutes, seconds = state.seconds)
        countDownTimer?.cancel()
        countDownTimer = null
    }

    private fun updateProgress(minutes: Long, seconds: Long): Float {
        val current = seconds * SECOND_MILLS + minutes * SECONDS_PER_MINUTE
        val remaining = totalSeconds - current
        return remaining.toFloat() * 360 / totalSeconds.toFloat()
    }

    private fun updateTimerSate(state: TimerState) {
        viewModelScope.launch {
            _timer.emit(state)
            _progress.emit(updateProgress(state.minutes, state.seconds))
        }
    }
}