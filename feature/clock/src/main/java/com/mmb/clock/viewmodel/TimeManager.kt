package com.mmb.clock.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mmb.clock.convertToTimerState
import com.mmb.clock.entity.TimerState
import javax.inject.Inject

class TimeManager @Inject constructor() {

    private val _progress: MutableLiveData<Float> = MutableLiveData()
    val progress: LiveData<Float> = _progress

    private val _timer: MutableLiveData<TimerState> = MutableLiveData()
    val timer: LiveData<TimerState> = _timer


    private var currentTimerState = TimerState()

    private var countDownTimer: CountDownTimer? = null

    fun initiate(session: TimerState) {
        currentTimerState = session
        updateTimerSate(currentTimerState, session)
    }

    fun startTimer(session: TimerState) {
        if (countDownTimer != null) return
        countDownTimer = object : CountDownTimer(
            currentTimerState.convertToSeconds(),
            TimerState.SECOND_MILLS
        ) {
            override fun onTick(millisUntilFinished: Long) {
                updateTimerSate(millisUntilFinished.convertToTimerState(), session)
            }

            override fun onFinish() {
                updateTimerSate(session.copy(finish = true), session)
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

    fun restartTimer(session: TimerState) {
        pauseTimer()
        currentTimerState = session
        updateTimerSate(currentTimerState, session)
    }

    private fun updateProgress(current: TimerState, session: TimerState): Float {
        val total = session.convertToSeconds()
        val remaining = total - current.convertToSeconds()
        return remaining.toFloat() / total.toFloat()
    }

    private fun updateTimerSate(current: TimerState, session: TimerState) {
        _timer.value = current
        _progress.value = updateProgress(current, session)
    }
}