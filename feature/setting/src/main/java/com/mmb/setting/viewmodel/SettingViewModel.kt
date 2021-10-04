package com.mmb.setting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.setting.datasource.SettingRepository
import com.mmb.setting.entity.SettingViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: SettingRepository,
) : ViewModel() {

    val settingViewState: Flow<SettingViewState> = repository.getSettings()

    val themeViewState: Flow<String> = settingViewState
        .map { it.theme }

    fun setSession(value: String) {
        viewModelScope.launch {
            repository.updateSessionDuration(value.toInt())
        }
    }

    fun setShortBreak(value: String) {
        viewModelScope.launch {
            repository.updateShortBreakDuration(value.toInt())
        }
    }

    fun setLongBreak(value: String) {
        viewModelScope.launch {
            repository.updateLongBreakDuration(value.toInt())
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