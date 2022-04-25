package com.mmb.session.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mmb.ui_compose.Layout

data class SessionIndicatorEntity(
    val title: String,
    val icon: ImageVector,
    val duration: Int,
    val active: Boolean,
)

@Composable
fun SessionIndicator(
    indicators: List<SessionIndicatorEntity>,
    modifier: Modifier = Modifier
) {
    val active = MaterialTheme.colors.primary
    val inactive = MaterialTheme.colors.primaryVariant

    val activeTextColor = MaterialTheme.colors.onSurface
    val inactiveTextColor = MaterialTheme.colors.onSecondary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(Layout.bodyMargin),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        indicators.forEach { indicator ->
            Column(
                modifier = modifier.wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val textColor = if (indicator.active) activeTextColor else inactiveTextColor
                val tint = if (indicator.active) active else inactive
                Text(
                    text = "${indicator.duration} Min",
                    fontSize = Layout.tinyFont,
                    color = textColor
                )
                Icon(
                    imageVector = indicator.icon,
                    tint = tint,
                    contentDescription = "",
                    modifier = modifier.size(25.dp)
                )
                Text(text = indicator.title, fontSize = Layout.tinyFont, color = textColor)
            }
        }
    }
}