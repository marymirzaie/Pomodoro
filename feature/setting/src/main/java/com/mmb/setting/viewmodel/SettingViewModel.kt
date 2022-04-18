package com.mmb.setting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.setting.datasource.SettingRepositoryImpl
import com.mmb.setting.entity.SettingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepositoryImpl,
) : ViewModel() {

    private val settingViewState: Flow<SettingViewState> = repository.getSettings()

    private val _setting: MutableLiveData<SettingViewState> = MutableLiveData()
    val setting: LiveData<SettingViewState> = _setting

    val sessionName = repository.getSessionName()

    val themeViewState: Flow<String> = settingViewState
        .map { it.theme }

    fun setSessionName(name: String) {
        viewModelScope.launch {
            repository.setSessionName(name)
        }
    }

    fun setFocusDuration(value: Int) {
        viewModelScope.launch {
            repository.updateSessionDuration(value)
        }
    }

    init {
        viewModelScope.launch {
            settingViewState.collect {
                _setting.value = it
            }
        }
    }

    fun setShortBreak(value: Int) {
        viewModelScope.launch {
            repository.updateShortBreakDuration(value)
        }
    }

    fun setLongBreak(value: Int) {
        viewModelScope.launch {
            repository.updateLongBreakDuration(value)
        }
    }

    fun setSessionCount(value: Int) {
        viewModelScope.launch {
            repository.updateSessionCount(value)
        }
    }

    fun enableSounds(enable: Boolean) {
        viewModelScope.launch {
            repository.enableSounds(enable)
        }
    }

    fun getAllThemes(): List<String> {
        return repository.getThemes()
    }

    fun setTheme(theme: String) {
        viewModelScope.launch {
            repository.updateTheme(theme)
        }
    }
}