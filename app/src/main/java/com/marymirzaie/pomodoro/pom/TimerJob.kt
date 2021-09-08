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

    private var job: Job? = null

    companion object {
        private const val MINUTE_MILLS = 60000L
        private const val MINUTES_PER_HOUR = 60
    }

    private fun <DisplayState> initTimer(
        total: Long,
        onTick: (Long) -> DisplayState,
    ): Flow<DisplayState> =
        (1 until total - 1).asFlow()
            .onEach { delay(MINUTE_MILLS) }
            .onStart { emit(0L) }
            .conflate()
            .transform { currentMinutes: Long ->
                emit(onTick(currentMinutes))
            }

    private fun calculateHourAndMinutes(min: Long): Pair<Long, Long> {
        val hour: Long = min / MINUTES_PER_HOUR
        val remaining = min - hour * MINUTES_PER_HOUR
        return hour to remaining
    }

    private fun getTotalMinutes(totalHours: Long, totalMinutes: Long): Long {
        return totalHours * MINUTES_PER_HOUR + totalMinutes
    }

    fun startTimer(totalHours: Long, totalMinutes: Long) {
        val totalTime = getTotalMinutes(totalHours, totalMinutes)
        if (job == null || job?.isCompleted == true) {
            job = coroutineScope.launch {
                initTimer(totalTime) { currentMinutes ->
                    val time = calculateHourAndMinutes(currentMinutes)
                    TimerState(hours = time.first, minutes = time.second)
                }.onCompletion {
                    _timerStateFlow.emit(TimerState(
                        minutes = totalMinutes,
                        hours = totalHours
                    ))
                }.collect {
                    _timerStateFlow.emit(it)
                }
            }
        }
    }

    fun cancelTimer() {
        job?.cancel()
    }
}