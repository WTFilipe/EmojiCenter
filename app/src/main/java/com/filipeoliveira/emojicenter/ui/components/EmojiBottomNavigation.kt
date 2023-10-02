package com.filipeoliveira.emojicenter.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.filipeoliveira.emojicenter.ui.screens.Screens

@Composable
fun EmojiBottomNavigation(
    navController: NavController,
    items: List<Screens>,
    modifier: Modifier = Modifier,
) {
    NavigationBar (
        modifier = modifier
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, null)},
                label = { EmojiText(stringResource(screen.title)) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true ,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun EmojiBottomNavigationPreview() {
    val screensInBottomNav = listOf(
        Screens.Home,
        Screens.Favorites,
        Screens.Search
    )
    EmojiBottomNavigation(rememberNavController(), screensInBottomNav)
}