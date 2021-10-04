package com.mmb.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmb.setting.entity.SettingViewState
import com.mmb.setting.viewmodel.SettingViewModel
import com.mmb.ui_compose.component.base.NumberTextField

@Composable
fun Setting() {
    SettingScreen(hiltViewModel())
}

@Composable
internal fun SettingScreen(
    viewModel: SettingViewModel,
) {
    Column {
        val setting = viewModel.settingViewState.collectAsState(SettingViewState())
        NumberTextField(
            label = "session duration",
            text = setting.value.sessionDuration
        ) {
            viewModel.setSession(it.toInt())
        }

        NumberTextField(
            label = "short break duration",
            text = setting.value.shortBreakDuration
        ) {
            viewModel.setShortBreak(it.toInt())
        }

        NumberTextField(
            label = "long break duration",
            text = setting.value.longBreakDuration
        ) {
            viewModel.setLongBreak(it.toInt())
        }
    }
}