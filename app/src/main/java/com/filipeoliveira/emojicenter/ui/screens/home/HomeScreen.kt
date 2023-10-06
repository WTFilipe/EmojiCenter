package com.filipeoliveira.emojicenter.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.emojicenter.R
import com.filipeoliveira.emojicenter.data.model.Emoji
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.ui.components.EmojiItemLeftLayout
import com.filipeoliveira.emojicenter.ui.components.EmojiItemRightLayout
import com.filipeoliveira.emojicenter.ui.UIState
import com.filipeoliveira.emojicenter.ui.components.EmojiError
import com.filipeoliveira.emojicenter.ui.components.EmojiText
import com.filipeoliveira.emojicenter.ui.theme.EmojiCenterTheme
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp
import com.filipeoliveira.emojicenter.ui.utils.ShimmerText

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {

    Surface {
        val uiState = viewModel.emojiList.collectAsState().value

        when (uiState) {
            is UIState.Success -> {
                OnSuccess(modifier, uiState.data)
            }

            is UIState.Error -> {
                OnError(modifier, uiState.error){
                    viewModel.refreshEmojis()
                }
            }

            is UIState.Loading -> {
                OnLoading(modifier)
            }
        }
    }
}

@Composable
private fun OnSuccess(modifier: Modifier = Modifier, data: List<Emoji>) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            ),
        contentPadding = PaddingValues(vertical = dimen16Dp)
    ) {
        items(data.size) { index ->
            if (index % 2 == 0) {
                EmojiItemRightLayout(emoji = data[index])
            } else {
                EmojiItemLeftLayout(emoji = data[index])
            }

            Spacer(modifier = Modifier.height(dimen16Dp))
        }
    }
}

@Composable
private fun OnLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            )
    ) {
        items(20) {
            ShimmerText(modifier = Modifier.fillMaxWidth(), fontSize = 50.sp)
            Spacer(modifier = Modifier.height(dimen16Dp))
        }
    }
}

@Composable
private fun OnError(
    modifier: Modifier = Modifier,
    error: Throwable,
    onErrorClicked: () -> Unit
) {
    val errorText = when(error) {
        is EmptyResponseException -> stringResource(R.string.empty_response)
        else -> stringResource(R.string.generic_error)
    }
    EmojiError(
        modifier = modifier
            .clickable {
                onErrorClicked()
            }
    ) {
        EmojiText(text = errorText)
    }
}

@Preview(showBackground = true)
@Composable
private fun OnSuccessPreview() {
    EmojiCenterTheme {
        OnSuccess(
            data = List(10){
                Emoji(
                    character = "\ud83d\ude03",
                    slug = "grinning-face-with-big-eyes",
                    unicodeName = "grinning face with big eyes"
                )
            }
        )
    }
}