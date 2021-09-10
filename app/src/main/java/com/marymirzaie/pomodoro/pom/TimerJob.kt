package com.marymirzaie.pomodoro.pom

import com.marymirzaie.pomodoro.pom.entity.TimerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.launch

class TimerJob(private val coroutineScope: CoroutineScope) {

    private val _timerStateFlow = MutableStateFlow(TimerState())
    val timerStateFlow: StateFlow<TimerState> = _timerStateFlow

    private val _progressStateFlow = MutableStateFlow(0f)
    val progressStateFlow: StateFlow<Float> = _progressStateFlow

    private var job: Job? = null
    private var initialTotalSeconds: Long = 0

    companion object {
        const val SECOND_MILLS = 1000L
        const val SECONDS_PER_MINUTE = 60
    }

    private fun <DisplayState> initTimer(
        total: Long,
        onTick: (Long) -> DisplayState,
    ): Flow<DisplayState> =
        (1 until total - 1).asFlow()
            .onEach { delay(SECOND_MILLS) }
            .onStart { emit(0L) }
            .conflate()
            .transform { currentMinutes: Long ->
                emit(onTick(currentMinutes))
            }

    private fun calculateMinuteAndSeconds(seconds: Long): Pair<Long, Long> {
        val min: Long = seconds / SECONDS_PER_MINUTE
        val remaining = seconds - min * SECONDS_PER_MINUTE
        return min to remaining
    }

    private fun getTotalSeconds(totalMinutes: Long, totalSeconds: Long): Long {
        return totalMinutes * SECONDS_PER_MINUTE + totalSeconds
    }

    private fun updateProgress(minutes: Long, seconds: Long): Float {
        val totalSeconds = seconds + minutes * SECONDS_PER_MINUTE
        return  totalSeconds.toFloat() * 360 / initialTotalSeconds.toFloat()
    }

    fun startTimer(totalMinutes: Long, totalSeconds: Long) {
        val totalTime = getTotalSeconds(totalMinutes, totalSeconds)
        initialTotalSeconds = totalTime
        if (job == null || job?.isCompleted == true) {
            job = coroutineScope.launch {
                initTimer(totalTime) { currentSeconds ->
                    val time = calculateMinuteAndSeconds(currentSeconds)
                    TimerState(minutes = time.first, seconds = time.second)
                }.onCompletion {
                    _progressStateFlow.emit(
                        updateProgress(minutes = totalMinutes,
                            seconds = totalSeconds))
                    _timerStateFlow.emit(TimerState(
                        minutes = totalMinutes,
                        seconds = totalSeconds
                    ))
                }.collect {
                    _progressStateFlow.emit(updateProgress(minutes = it.minutes,
                        seconds = it.seconds))
                    _timerStateFlow.emit(it)
                }
            }
        }
    }

    fun cancelTimer() {
        job?.cancel()
    }
}