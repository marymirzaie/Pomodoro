package com.mmb.setting.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.mmb.core.dispatcher.Dispatcher
import com.mmb.setting.datasource.SettingRepositoryImpl
import com.mmb.setting.entity.SettingSerializer
import com.mmb.setting_proto.Setting
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SettingModule {

    @Provides
    @Singleton
    fun provideSettingRepository(
        dispatcher: Dispatcher,
        @ApplicationContext context: Context,
    ): SettingRepositoryImpl {
        return SettingRepositoryImpl(
            dispatcher,
            context.userPreferencesStore
        )
    }
}

private val Context.userPreferencesStore: DataStore<Setting> by dataStore(
    fileName = SettingSerializer.DATA_STORE_FILE_NAME,
    serializer = SettingSerializer
)