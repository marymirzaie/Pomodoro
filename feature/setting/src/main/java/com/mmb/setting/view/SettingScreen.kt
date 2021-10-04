package com.mmb.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmb.setting.entity.SettingViewState
import com.mmb.setting.viewmodel.SettingViewModel
import com.mmb.ui_compose.Layout
import com.mmb.ui_compose.component.base.NumberTextRow
import com.mmb.ui_compose.component.dialogue.RadioDialogueRow
import com.mmb.ui_compose.component.swtich.SwitchRow

@Composable
fun Setting() {
    SettingScreen(hiltViewModel())
}

@Composable
internal fun SettingScreen(
    viewModel: SettingViewModel,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = Layout.bodyMargin)
            .fillMaxWidth()
    ) {
        val setting = viewModel.settingViewState.collectAsState(SettingViewState())
        NumberTextRow(
            label = "session duration",
            text = setting.value.sessionDuration
        ) {
            if (it.isNotEmpty()) {
                viewModel.setSession(it)
            }
        }

        NumberTextRow(
            label = "short break duration",
            text = setting.value.shortBreakDuration
        ) {
            if (it.isNotEmpty()) {
                viewModel.setShortBreak(it)
            }
        }

        NumberTextRow(
            label = "long break duration",
            text = setting.value.longBreakDuration
        ) {
            if (it.isNotEmpty()) {
                viewModel.setLongBreak(it)
            }
        }

        RadioDialogueRow(
            "Theme",
            setting.value.theme,
            viewModel.getAllThemes(),
            {
                viewModel.setTheme(it)
            }
        )

        SwitchRow(title = "Enable Sounds", isEnabled = setting.value.enableSounds) {
            viewModel.enableSounds(it)
        }

    }
}