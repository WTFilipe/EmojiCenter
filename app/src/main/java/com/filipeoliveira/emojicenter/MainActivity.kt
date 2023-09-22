package com.filipeoliveira.emojicenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.filipeoliveira.emojicenter.ui.EmojiError
import com.filipeoliveira.emojicenter.ui.EmojiLoading
import com.filipeoliveira.emojicenter.ui.EmojiText
import com.filipeoliveira.emojicenter.ui.theme.EmojiCenterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiCenterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainActivityScreen()
                }
            }
        }
    }
}

@Composable
fun MainActivityScreen(modifier: Modifier = Modifier) {
    EmojiError {
        EmojiText(text = "Erro")
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier){
    EmojiLoading(modifier) {
        EmojiText(text = "Carregando")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmojiCenterTheme {
        MainActivityScreen()
    }
}