package com.mmb.ui_compose.component.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NumberTextRow(
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
        modifier = modifier.fillMaxWidth(),
        onValueChange = onChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Preview
@Composable
fun NumberTextRowPreview() {
    NumberTextRow("Time", text = "25") {}
}