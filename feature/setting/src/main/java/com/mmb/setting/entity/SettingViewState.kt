package com.mmb.setting.entity

import com.mmb.setting.datasource.SettingRepository
import com.mmb.setting.entity.SettingDefaults.DEFAULT_LONG_BREAK
import com.mmb.setting.entity.SettingDefaults.DEFAULT_SESSION
import com.mmb.setting.entity.SettingDefaults.DEFAULT_SHORT_BREAK

data class SettingViewState(
    val sessionDuration: String = DEFAULT_SESSION.toString(),
    val shortBreakDuration: String = DEFAULT_SHORT_BREAK.toString(),
    val longBreakDuration: String = DEFAULT_LONG_BREAK.toString(),
    val enableSounds: Boolean = true,
    val theme: String = SettingRepository.SYSTEM_DEFAULT_THEME,
) {
}