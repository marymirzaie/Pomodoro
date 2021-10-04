package com.mmb.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mmb.ui_compose.AppBarAlphas

@Composable
internal fun BottomNav(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    currentScreen: Screen,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(
        contentColor = contentColorFor(MaterialTheme.colors.surface),
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = AppBarAlphas.translucentBarAlpha()),
    ) {
        allScreens.forEach {
            val selected = currentScreen == it
            BottomNavigationItem(selected = selected, onClick = {
                onTabSelected(it)
            }, icon = {
                Icon(imageVector = it.icon,
                    contentDescription = stringResource(id = R.string.click_bottom_navigation)
                        .format(it.route)
                )
            })
        }

    }
}

@Preview
@Composable
internal fun BottomNavigationPreview() {
    BottomNav(Screen.allScreens(), {}, Screen.Clock)
}
