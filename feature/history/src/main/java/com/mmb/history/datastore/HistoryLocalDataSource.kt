package com.mmb.history.datastore

import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class HistoryLocalDataSource @Inject constructor(
    private val historyDao: HistoryDao,
) {

    fun sessionCompleted(date: String) {
        historyDao.addCompletedSession(date)
    }
}