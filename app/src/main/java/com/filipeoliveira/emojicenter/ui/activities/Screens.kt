package com.filipeoliveira.emojicenter.ui.activities

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.filipeoliveira.emojicenter.R

sealed class Screens(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    object Home : Screens("home", R.string.title_home, Icons.Default.Home)
}