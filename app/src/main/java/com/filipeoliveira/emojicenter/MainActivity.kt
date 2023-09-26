package com.filipeoliveira.emojicenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filipeoliveira.emojicenter.ui.EmojiError
import com.filipeoliveira.emojicenter.ui.EmojiLoading
import com.filipeoliveira.emojicenter.ui.EmojiText
import com.filipeoliveira.emojicenter.ui.EmojiViewModel
import com.filipeoliveira.emojicenter.ui.UIState
import com.filipeoliveira.emojicenter.ui.theme.EmojiCenterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var emojiViewModel: EmojiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmojiCenterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainActivityScreen(viewModel = emojiViewModel)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        emojiViewModel.getEmojiList()
    }
}

@Composable
fun MainActivityScreen(
    modifier: Modifier = Modifier,
    viewModel: EmojiViewModel = viewModel()
) {
    val uiState = viewModel.emojiList.collectAsState().value

    when(uiState){
        is UIState.Success -> {
        }
        is UIState.Error -> {
            OnError(modifier, uiState.error)
        }
        is UIState.Loading -> {
            OnLoading(modifier)
        }
    }
}

@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    EmojiLoading(modifier) {
        EmojiText(text = "Carregando")
    }
}

@Composable
fun OnError(modifier: Modifier = Modifier, errors: Throwable) {
    EmojiError {
        EmojiText(text = "Erro")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EmojiCenterTheme {
        MainActivityScreen()
    }
}