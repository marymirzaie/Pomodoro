package com.mmb.setting.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mmb.setting.viewmodel.SettingViewModel
import com.mmb.ui_compose.Layout

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
            .padding(horizontal = 16.dp, vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Pomodoro",
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = Layout.largeFontSize
        )
        Text(text = "Name", modifier = Modifier.padding(top = 8.dp))
        val sessionName = viewModel.sessionName.collectAsState(initial = "").value
        OutlinedTextField(
            value = sessionName,
            onValueChange = viewModel::setSessionName,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            singleLine = true,
        )

        viewModel.setting.observeAsState().value?.let {
            Slider("Focus time", it.focusDuration, viewModel::setFocusDuration, "Min", 60)
            Slider(
                "Short break time", it.shortBreakDuration, viewModel::setShortBreak, "Min", 60
            )
            Slider("Long break time", it.longBreakDuration, viewModel::setLongBreak, "Min", 60)
            Slider("Set of pomodoro", it.sessionCount, viewModel::setSessionCount, "Poms", 10)
        }

    }
}