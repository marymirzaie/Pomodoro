package com.mmb.setting.entity

import com.mmb.setting.datasource.SettingRepository

data class SettingViewState(
    val sessionDuration: String = "",
    val shortBreakDuration: String = "",
    val longBreakDuration: String = "",
    val enableSounds: Boolean = true,
    val theme: String = SettingRepository.SYSTEM_DEFAULT_THEME,
) {
}