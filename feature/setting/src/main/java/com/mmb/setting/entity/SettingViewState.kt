package com.mmb.setting.entity

import com.mmb.setting.datasource.SettingRepositoryImpl
import com.mmb.setting.entity.SettingDefaults.DEFAULT_LONG_BREAK
import com.mmb.setting.entity.SettingDefaults.DEFAULT_SESSION
import com.mmb.setting.entity.SettingDefaults.DEFAULT_SESSION_COUNT
import com.mmb.setting.entity.SettingDefaults.DEFAULT_SHORT_BREAK

data class SettingViewState(
    val focusDuration: Int = DEFAULT_SESSION,
    val shortBreakDuration: Int = DEFAULT_SHORT_BREAK,
    val longBreakDuration: Int = DEFAULT_LONG_BREAK,
    val sessionCount: Int = DEFAULT_SESSION_COUNT,
    val enableSounds: Boolean = true,
    val theme: String = SettingRepositoryImpl.SYSTEM_DEFAULT_THEME,
) {
}