package com.mmb.setting.datasource

import androidx.datastore.core.DataStore
import com.mmb.core.dispatcher.Dispatcher
import com.mmb.setting.entity.SettingDefaults
import com.mmb.setting.entity.SettingViewState
import com.mmb.setting_proto.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepository @Inject constructor(
    private val dispatcher: Dispatcher,
    private val dataStore: DataStore<Setting>,
) {

    private suspend fun updateData(
        transform: (Setting.Builder) -> Setting.Builder,
    ) {
        withContext(context = dispatcher.iO) {
            dataStore.updateData { preferences ->
                val builder = preferences.toBuilder()
                transform.invoke(builder).build()
            }
        }
    }

    suspend fun updateSessionDuration(duration: Int) {
        updateData {
            it.setSessionDuration(duration)
        }
    }

    suspend fun updateSessionCount(value: Int) {
        updateData {
            it.setSessionCount(value)
        }
    }

    suspend fun updateShortBreakDuration(duration: Int) {
        updateData {
            it.setShortBreakDuration(duration)
        }
    }

    suspend fun updateLongBreakDuration(duration: Int) {
        updateData {
            it.setLongBreakDuration(duration)
        }
    }

    suspend fun updateTheme(theme: String) {
        val convertedTheme = when (theme) {
            DARK_THEME -> Setting.THEME.DARK
            LIGHT_THEME -> Setting.THEME.LIGHT
            SYSTEM_DEFAULT_THEME -> Setting.THEME.SYSTEM_DEFAULT
            else -> Setting.THEME.SYSTEM_DEFAULT
        }
        updateData {
            it.setTheme(convertedTheme)
        }
    }

    suspend fun enableSounds(enable: Boolean) {
        updateData {
            it.setEnableSounds(enable)
        }
    }

    fun getSettings(): Flow<SettingViewState> {
        return mapToSettingsViewState(dataStore.data)
    }

    fun getThemes(): List<String> {
        return listOf(DARK_THEME, LIGHT_THEME, SYSTEM_DEFAULT_THEME)
    }

    fun getFocusDuration(): Flow<Int> {
        return dataStore.data.map {
            if (it.sessionDuration == 0) SettingDefaults.DEFAULT_SESSION else it.sessionDuration
        }
    }


    private fun mapToSettingsViewState(settingProto: Flow<Setting>): Flow<SettingViewState> {
        return settingProto.map {
            val theme = when (it.theme) {
                Setting.THEME.SYSTEM_DEFAULT -> SettingRepository.SYSTEM_DEFAULT_THEME
                Setting.THEME.DARK -> SettingRepository.DARK_THEME
                Setting.THEME.LIGHT -> SettingRepository.LIGHT_THEME
                else -> SettingRepository.SYSTEM_DEFAULT_THEME
            }

            SettingViewState(
                it.sessionDuration,
                it.shortBreakDuration,
                it.longBreakDuration,
                it.sessionCount,
                it.enableSounds,
                theme
            )
        }
    }

    companion object {
        const val DARK_THEME = "Dark"
        const val LIGHT_THEME = "Light"
        const val SYSTEM_DEFAULT_THEME = "System default"
    }
}