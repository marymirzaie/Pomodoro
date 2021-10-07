package com.mmb.history.datastore

import androidx.room.Dao
import androidx.room.Query
import com.mmb.history.datastore.HistoryEntity.Companion.HISTORY_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("UPDATE $HISTORY_TABLE_NAME SET sessionsCompleted=sessionsCompleted+1 WHERE date = :date")
    fun addCompletedSession(date: String)

    @Query("SELECT * FROM $HISTORY_TABLE_NAME")
    fun getHistory(): Flow<List<HistoryEntity>>
}