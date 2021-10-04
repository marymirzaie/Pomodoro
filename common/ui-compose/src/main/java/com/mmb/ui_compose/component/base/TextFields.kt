package com.mmb.ui_compose.component.base

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PomText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h3,
        modifier = modifier.wrapContentSize(),
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun NumberTextField(
    label: String,
    modifier: Modifier = Modifier,
    text: String = "",
    onChange: (String) -> Unit,
) {
    OutlinedTextField(
        label = {
            Text(text = label)
        },
        value = text,
        onValueChange = { newValue ->
            onChange.invoke(newValue)
        },
        modifier = modifier,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}
