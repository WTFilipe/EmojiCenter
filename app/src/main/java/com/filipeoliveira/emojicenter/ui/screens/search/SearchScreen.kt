package com.filipeoliveira.emojicenter.ui.screens.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.emojicenter.data.model.Emoji
import com.filipeoliveira.emojicenter.ui.components.EmojiCategory
import com.filipeoliveira.emojicenter.ui.components.EmojiSearchBar
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen8Dp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        SearchScreenSearchBar()
        SearchScreenContent(viewModel)
    }
}

@Composable
fun SearchScreenSearchBar(modifier: Modifier = Modifier) {
    var searchFieldValue by rememberSaveable{
        mutableStateOf("")
    }
    EmojiSearchBar(
        value = searchFieldValue,
        modifier = modifier
            .padding(start = dimen16Dp, top = dimen16Dp, end = dimen16Dp)
    ) { query ->
        searchFieldValue = query
    }
}

@Composable
private fun SearchScreenContent(viewModel: SearchViewModel) {
    val uiState = viewModel.categoryAndEmojis.collectAsState().value

    when {
        uiState.areCategoriesLoading -> OnCategoriesLoading()
        uiState.error != null -> OnCategoriesError(uiState.error)
        else -> OnCategoriesSuccess(uiState.categoryAndEmojisList)
    }
}

@Composable
fun OnCategoriesSuccess(data: List<CategoryAndEmojis>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = dimen16Dp)
    ) {
        items(data.size) {
            EmojiCategory(data[it])
        }
    }
}

@Composable
fun OnCategoriesError(error: Throwable, modifier: Modifier = Modifier) {
}

@Composable
fun OnCategoriesLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            ),
        contentPadding = PaddingValues(vertical = dimen16Dp)
    ) {
        items(20) {
            EmojiCategory(
                CategoryAndEmojis(
                    title = "",
                    isTitleLoading = true,
                    areEmojisLoading = true,
                    emojis = listOf()
                )
            )

            Spacer(modifier = Modifier.height(dimen8Dp))
        }
    }
}

@Preview(showBackground = true, heightDp = 150)
@Composable
fun CategoriesLoadingPreview() {
    OnCategoriesLoading()
}

@Preview(showBackground = true)
@Composable
fun CategoriesSuccessPreview() {
    OnCategoriesSuccess(
        data = listOf(
            CategoryAndEmojis(
                title = "Categoria",
                emojis = List(50) {
                    Emoji(
                        character = "\ud83d\ude03",
                        slug = "grinning-face-with-big-eyes",
                        unicodeName = "grinning face with big eyes"
                    )
                },
                areEmojisLoading = false,
                isTitleLoading = false
            ),
            CategoryAndEmojis(
                title = "Categoria",
                emojis = List(50) {
                    Emoji(
                        character = "\ud83d\ude03",
                        slug = "grinning-face-with-big-eyes",
                        unicodeName = "grinning face with big eyes"
                    )
                },
                areEmojisLoading = false,
                isTitleLoading = false
            )
        )
    )
}