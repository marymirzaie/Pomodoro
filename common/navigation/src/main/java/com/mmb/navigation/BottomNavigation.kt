package com.mmb.navigation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun BottomNavigation(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    currentScreen: Screen,
) {
    BottomAppBar(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 20.dp
    ) {
        Row(horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()) {
            allScreens.forEach {
                Row {
                    val tint = if (currentScreen == it) {
                        MaterialTheme.colors.primary
                    } else LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                    Icon(imageVector = it.icon,
                        contentDescription = stringResource(id = R.string.click_bottom_navigation)
                            .format(it.route),
                        tint = tint,
                        modifier = Modifier.clickable { onTabSelected(it) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
internal fun BottomNavigationPreview() {
    BottomNavigation(Screen.allScreens(), {}, Screen.Clock)
}
