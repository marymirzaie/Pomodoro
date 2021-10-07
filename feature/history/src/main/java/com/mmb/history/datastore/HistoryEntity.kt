package com.mmb.history.datastore

import androidx.room.Entity
import com.mmb.history.datastore.HistoryEntity.Companion.HISTORY_TABLE_NAME

@Entity(tableName = HISTORY_TABLE_NAME)
data class HistoryEntity(
    val sessionsCompleted: Int,
    val date: String,
) {
    companion object {
        const val HISTORY_TABLE_NAME = "history_table"
    }
}