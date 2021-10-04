package com.mmb.setting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.setting.datasource.SettingRepository
import com.mmb.setting.entity.SettingViewState
import com.mmb.setting_proto.Setting
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
        .map { mapToSettingsViewState(it) }

    fun setSession(value: Int) {
        viewModelScope.launch {
            repository.updateSessionDuration(value)
        }
    }

    fun setShortBreak(value: Int) {
        viewModelScope.launch {
        }
    }

    fun setLongBreak(value: Int) {

    }

    fun enableSoundsWhenDone(enable: Boolean) {

    }

    fun getAllThemes(): List<String> {
        return repository.getThemes()
    }

    fun setTheme(theme: String) {
        viewModelScope.launch {
            repository.updateTheme(theme)
        }
    }

    private fun mapToSettingsViewState(settingProto: Setting): SettingViewState {
        val theme = when (settingProto.theme) {
            Setting.THEME.SYSTEM_DEFAULT -> SettingRepository.SYSTEM_DEFAULT_THEME
            Setting.THEME.DARK -> SettingRepository.DARK_THEME
            Setting.THEME.LIGHT -> SettingRepository.LIGHT_THEME
            else -> SettingRepository.SYSTEM_DEFAULT_THEME
        }
        return SettingViewState(
            settingProto.sessionDuration.toString(),
            settingProto.shortBreakDuration.toString(),
            settingProto.longBreakDuration.toString(),
            settingProto.enableSounds,
            theme
        )
    }
}