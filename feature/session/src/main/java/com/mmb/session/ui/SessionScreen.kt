package com.mmb.session.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mmb.session.SessionViewModel

@Composable
fun Session(
    viewModel: SessionViewModel,
    modifier: Modifier = Modifier
) {
    viewModel.subscribe()
    SessionScreen(viewModel, modifier)
}

@Composable
internal fun SessionScreen(
    viewModel: SessionViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.wrapContentSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val pomCount = viewModel.pomCount.collectAsState(initial = 0).value
        val completed = viewModel.completedPom.observeAsState(initial = 0).value
        Text(
            text = "$completed of $pomCount",
            modifier = Modifier.padding(vertical = 16.dp),
            fontSize = 20.sp
        )

        SessionIndicator(viewModel.indicators.observeAsState(listOf()).value)
    }
}