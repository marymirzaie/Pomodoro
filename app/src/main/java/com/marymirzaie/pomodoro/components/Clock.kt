package com.marymirzaie.pomodoro.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.marymirzaie.pomodoro.R
import com.marymirzaie.pomodoro.entity.TimerState

@Composable
//a clock that goes through by time
fun PomodoroClock(timerState: TimerState, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .padding(16.dp)
        .fillMaxSize()
        .aspectRatio(1f)
        .shadow(elevation = 12.dp, shape = CircleShape)
        .clip(CircleShape)
        .background(MaterialTheme.colors.surface, shape = CircleShape)
        .padding(4.dp),
        contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            //todo read about this
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                PomodoroClockText(timerState)
                PomodoroCount()
            }
        }
    }
}

@Composable
fun PomodoroClockText(timerState: TimerState, modifier: Modifier = Modifier) {
    Text(
        text = "${timerState.minutes}:${timerState.seconds}",
        style = MaterialTheme.typography.h3,
        modifier = modifier.wrapContentSize(),
        color = MaterialTheme.colors.primary
    )
}

@Composable
//consists of 3 pomodoro icons that shows how many times
fun PomodoroCount(
    modifier: Modifier = Modifier,
    numberOfPoms: Int = 4,
    pomsCompleted: Int = 3,
) {
    //todo use tint properly
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {

        var comp = pomsCompleted
        for (index in 0 until numberOfPoms) {
            val tint = if (comp != 0) {
                comp -= 1
                Color.Unspecified
            } else LocalContentColor.current.copy(alpha = LocalContentAlpha.current)

            Icon(painter = painterResource(id = R.drawable.ic_pomodoro_tomato),
                tint = tint,
                contentDescription = null,
                modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
@Preview
//a clock that goes through by time
fun PomodoroClockPreview() {
    PomodoroClock(timerState = TimerState(3, 15))
}

@Composable
@Preview
fun PomodoroCountPreview() {
    PomodoroCount()
}