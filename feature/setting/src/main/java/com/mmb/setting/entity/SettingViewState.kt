package com.mmb.setting.entity

import com.mmb.setting.datasource.SettingRepository

data class SettingViewState(
    val sessionDuration: String = "25",
    val shortBreakDuration: String = "10",
    val longBreakDuration: String = "15",
    val enableSounds: Boolean = true,
    val theme: String = SettingRepository.SYSTEM_DEFAULT_THEME,
) {
}