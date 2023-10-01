package com.filipeoliveira.emojicenter.ui.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.filipeoliveira.emojicenter.R

sealed class Screens(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Home : Screens("home", R.string.title_home, Icons.Default.Home)
    object Favorites : Screens("favorite", R.string.title_favorite, Icons.Default.Favorite)
    object Search : Screens("search", R.string.title_search, Icons.Default.Search)
}