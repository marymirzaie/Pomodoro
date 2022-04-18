package com.mmb.core

import kotlinx.coroutines.flow.Flow

interface SettingRepository {

    fun getSessionName(): Flow<String>

    fun getFocusDuration(): Flow<Int>
}