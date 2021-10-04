package com.mmb.setting.entity

data class SettingViewState(
    val sessionDuration: String = "",
    val shortBreakDuration: String = "",
    val longBreakDuration: String = "",
    val enableSounds: Boolean = true,
    val theme: THEME = THEME.SYSTEM_DEFAULT,
) {
    enum class THEME {
        SYSTEM_DEFAULT,
        DARK,
        LIGHT
    }
}