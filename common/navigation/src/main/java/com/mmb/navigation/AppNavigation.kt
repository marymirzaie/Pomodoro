package com.mmb.navigation

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonOutline
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mmb.clock.Clock
import com.mmb.setting.view.Setting
import com.mmb.ui_compose.theme.PomodoroTheme

internal sealed class Screen(val route: String, val icon: ImageVector) {
    fun createRoute(root: Screen) = "${root.route}/$route"

    object Clock : Screen("clock", Icons.Filled.Timer)
    object Settings : Screen("settings", Icons.Filled.Settings)
    object Profile : Screen("profile", Icons.Filled.PersonOutline)

    companion object {
        fun fromRoute(route: String?): Screen {
            return when (route?.substringBefore("/")) {
                Clock.route -> Clock
                Settings.route -> Settings
                Profile.route -> Profile
                null -> Clock
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
        }

        fun allScreens() = listOf(Settings, Clock, Profile)
    }
}

@Composable
fun PomodoroAppNavigation() {
    isSystemInDarkTheme()
    PomodoroTheme(darkTheme = isSystemInDarkTheme()) {
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val onTabSelected = { screen: Screen ->
            navController.navigate(screen.route)
        }

        Scaffold() { innerPadding ->
            AppNavigation(navController, Modifier.padding(innerPadding))
        }
    }
}

@Composable
internal fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Clock.route,
        modifier = modifier
    ) {
        addProfileScreen()
        addClockScreen(navController)
        addSettingsScreen()
    }
}

private fun NavGraphBuilder.addClockScreen(navController: NavHostController) {
    composable(route = Screen.Clock.route) {
        Clock(navigateToSettings = { navController.navigate(Screen.Settings.route) })
    }
}

private fun NavGraphBuilder.addSettingsScreen() {
    composable(route = Screen.Settings.route) {
        Setting()
    }
}

private fun NavGraphBuilder.addProfileScreen() {
    composable(route = Screen.Profile.route) {

    }
}