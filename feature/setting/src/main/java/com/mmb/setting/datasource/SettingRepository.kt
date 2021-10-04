package com.mmb.setting.datasource

import androidx.datastore.core.DataStore
import com.mmb.core.dispatcher.Dispatcher
import com.mmb.setting_proto.Setting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingRepository @Inject constructor(
    private val dispatcher: Dispatcher,
    private val dataStore: DataStore<Setting>,
) {

    suspend fun updateSessionDuration(duration: Int) {
        withContext(context = dispatcher.iO) {
            dataStore.updateData { preferences ->
                preferences.toBuilder().setSessionDuration(duration).build()
            }
        }
    }

    suspend fun updateTheme(theme: String) {
        val convertedTheme = when (theme) {
            DARK_THEME -> Setting.THEME.DARK
            LIGHT_THEME -> Setting.THEME.LIGHT
            SYSTEM_DEFAULT_THEME -> Setting.THEME.SYSTEM_DEFAULT
            else -> Setting.THEME.SYSTEM_DEFAULT
        }
        withContext(context = dispatcher.iO) {
            dataStore.updateData { preferences ->
                preferences.toBuilder().setTheme(convertedTheme).build()
            }
        }
    }

    fun getSettings(): Flow<Setting> {
        return dataStore.data
    }

    fun getThemes(): List<String> {
        return listOf(DARK_THEME, LIGHT_THEME, SYSTEM_DEFAULT_THEME)
    }

    companion object {
        const val DARK_THEME = "Dark"
        const val LIGHT_THEME = "Light"
        const val SYSTEM_DEFAULT_THEME = "System default"
    }
}