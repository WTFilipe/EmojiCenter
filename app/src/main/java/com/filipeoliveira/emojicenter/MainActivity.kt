package com.filipeoliveira.emojicenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filipeoliveira.emojicenter.ui.EmojiViewModel
import com.filipeoliveira.emojicenter.ui.activities.HomeScreen
import com.filipeoliveira.emojicenter.ui.activities.Screens
import com.filipeoliveira.emojicenter.ui.components.EmojiBottomNavigation
import com.filipeoliveira.emojicenter.ui.theme.EmojiCenterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var emojiViewModel: EmojiViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiCenterTheme {
                val navController = rememberNavController()

                val screensInBottomNav = listOf(Screens.Home)

                Scaffold(
                    bottomBar = { EmojiBottomNavigation(navController, screensInBottomNav) }
                ) {paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screens.Home.route,
                        modifier = Modifier.padding(paddingValues)
                    ){
                        composable("home") { HomeScreen(viewModel = emojiViewModel) }
                    }
                }




            }
        }

        emojiViewModel.getEmojiList()
    }
}

