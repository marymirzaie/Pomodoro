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

    fun getSettings(): Flow<Setting> {
        return dataStore.data
    }
}