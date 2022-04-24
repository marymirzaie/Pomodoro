package com.mmb.session

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.sharp.Computer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.core.SettingRepository
import com.mmb.session.ui.SessionIndicatorEntity
import com.mmb.session.ui.SessionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SessionViewModel @Inject constructor(
    private val settingRepository: SettingRepository,
) : ViewModel() {

    val pomCount = settingRepository.getFullPomCount()

    private val _sessionType: MutableLiveData<SessionState> = MutableLiveData()
    val sessionType: LiveData<SessionState> = _sessionType

    private val _sessionDuration: MutableLiveData<Int> = MutableLiveData()
    val sessionDuration: LiveData<Int> = _sessionDuration

    private var currentSessionType: SessionState = SessionState.FOCUS

    private val _completedPom: MutableLiveData<Int> = MutableLiveData()
    val completedPom: LiveData<Int> = _completedPom

    //expose duration to view
    private val _indicators: MutableLiveData<List<SessionIndicatorEntity>> = MutableLiveData()
    val indicators: LiveData<List<SessionIndicatorEntity>> = _indicators

    fun subscribe() {
        // use last session type that user was in
        updateSessionType(SessionState.FOCUS)
        getIndicators()
    }

    private fun getIndicators() {
        val indicators = arrayListOf<SessionIndicatorEntity>()
        viewModelScope.launch {
            settingRepository.getFocusDuration().collect { duration ->
                indicators.add(
                    SessionIndicatorEntity(
                        title = "Focus",
                        duration = duration,
                        icon = Icons.Sharp.Computer,
                        active = currentSessionType == SessionState.FOCUS
                    )
                )
                _indicators.postValue(indicators)
            }
        }
        viewModelScope.launch {
            settingRepository.getShortBreakDuration().collect { duration ->
                indicators.add(
                    SessionIndicatorEntity(
                        title = "Short Break",
                        duration = duration,
                        icon = Icons.Filled.LocalCafe,
                        active = currentSessionType == SessionState.SHORT_BREAK
                    )
                )
                _indicators.postValue(indicators)
            }
        }
        viewModelScope.launch {
            settingRepository.getLongBreakDuration().collect { duration ->
                indicators.add(
                    SessionIndicatorEntity(
                        title = "Long Break",
                        duration = duration,
                        icon = Icons.Filled.Chair,
                        active = currentSessionType == SessionState.LONG_BREAK
                    )
                )
                _indicators.postValue(indicators)
            }
        }
    }

    fun onSessionCompleted() {
        when (currentSessionType) {
            SessionState.FOCUS -> onFocusFinish()
            SessionState.LONG_BREAK -> onLongBreakFinish()
            else -> onShortBreakFinish()
        }
    }

    private fun onFocusFinish() {
        viewModelScope.launch {
            pomCount.collect { count ->
                val currentPomState = _completedPom.value ?: 1
                if (currentPomState == count) {
                    updateSessionType(SessionState.LONG_BREAK)
                } else {
                    updateSessionType(SessionState.SHORT_BREAK)
                }
            }
        }
    }

    private fun onShortBreakFinish() {
        updateSessionType(SessionState.FOCUS)
        val currentPomState = _completedPom.value ?: 1
        _completedPom.value = currentPomState + 1
    }

    private fun onLongBreakFinish() {
        viewModelScope.launch {
            pomCount.collect {
                _completedPom.value = 1
            }
        }
        updateSessionType(SessionState.FOCUS)
        val currentPomState = _completedPom.value ?: 1
        _completedPom.value = currentPomState + 1
    }

    private fun updateSessionType(type: SessionState) {
        currentSessionType = type
        _sessionType.value = currentSessionType
        val session = when (currentSessionType) {
            SessionState.FOCUS -> settingRepository.getFocusDuration()
            SessionState.LONG_BREAK -> settingRepository.getLongBreakDuration()
            else -> settingRepository.getShortBreakDuration()
        }
        viewModelScope.launch {
            session.collect {
                _sessionDuration.postValue(it)
            }
        }
    }
}