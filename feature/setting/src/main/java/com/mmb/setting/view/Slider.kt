package com.mmb.setting.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.math.roundToInt

@Composable
fun Slider(
    label: String,
    initialValue: Int,
    onValueChange: (Int) -> Unit,
    valuePlaceHolder: String = "",
    maxValue: Int
) {
    val initialSliderPosition = (initialValue / maxValue.toFloat())
    val sliderPosition = remember { mutableStateOf(initialSliderPosition) }
    val intPosition = (sliderPosition.value * maxValue).roundToInt()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = label)
            Text(text = "$intPosition $valuePlaceHolder")
        }
        androidx.compose.material.Slider(
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
                onValueChange(intPosition)
            }
        )
    }
}