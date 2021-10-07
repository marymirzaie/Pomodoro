package com.mmb.history.datastore

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HistoryEntity::class], version = 1)
internal abstract class HistoryDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao
}