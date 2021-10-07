package com.mmb.history.di

import android.content.Context
import androidx.room.Room
import com.mmb.history.datastore.HistoryDao
import com.mmb.history.datastore.HistoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
internal class HistoryModule {

    @Provides
    fun provideHistoryDatabase(@ApplicationContext context: Context): HistoryDatabase {
        return Room.databaseBuilder(context, HistoryDatabase::class.java, "history_database")
            .build()
    }

    @Provides
    fun provideHistoryDao(database: HistoryDatabase): HistoryDao {
        return database.historyDao()
    }
}