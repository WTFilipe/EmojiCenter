package com.filipeoliveira.emojicenter.ui.activities

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filipeoliveira.emojicenter.data.Emoji
import com.filipeoliveira.emojicenter.ui.EmojiViewModel
import com.filipeoliveira.emojicenter.ui.HomeEmojiItemLeft
import com.filipeoliveira.emojicenter.ui.HomeEmojiItemRight
import com.filipeoliveira.emojicenter.ui.UIState
import com.filipeoliveira.emojicenter.ui.components.EmojiError
import com.filipeoliveira.emojicenter.ui.components.EmojiLoading
import com.filipeoliveira.emojicenter.ui.components.EmojiText
import com.filipeoliveira.emojicenter.ui.theme.EmojiCenterTheme
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: EmojiViewModel = viewModel()
) {

    Surface {
        val uiState = viewModel.emojiList.collectAsState().value

        when(uiState){
            is UIState.Success -> {
                OnSuccess(uiState.data)
            }
            is UIState.Error -> {
                OnError(modifier, uiState.error)
            }
            is UIState.Loading -> {
                OnLoading(modifier)
            }
        }
    }
}

@Composable
fun OnSuccess(data: List<Emoji>) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            )
    ) {
        items(data.size){index ->
            if (index % 2 == 0){
                HomeEmojiItemRight(emoji = data[index])
            } else {
                HomeEmojiItemLeft(emoji = data[index])
            }

            Spacer(modifier = Modifier.height(dimen16Dp))
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
fun HomeScreenPreview() {
    EmojiCenterTheme {
        HomeScreen()
    }
}