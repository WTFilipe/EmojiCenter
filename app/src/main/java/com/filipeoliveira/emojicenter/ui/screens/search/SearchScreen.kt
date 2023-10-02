package com.filipeoliveira.emojicenter.ui.screens.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.emojicenter.ui.components.EmojiCategory
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen8Dp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Surface(modifier = modifier) {
        val uiState = viewModel.categoryAndEmojis.collectAsState().value

        when {
            uiState.areCategoriesLoading -> OnCategoriesLoading()
            uiState.error != null -> OnCategoriesError(uiState.error)
            else -> OnCategoriesSuccess(uiState.categoryAndEmojisList)
        }
    }
}

@Composable
fun OnCategoriesSuccess(data: List<CategoryAndEmojis>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            )
    ) {
        items(data.size) {
            EmojiCategory(data[it])
        }
    }
}

@Composable
fun OnCategoriesError( error: Throwable, modifier: Modifier = Modifier) {
    TODO("Not yet implemented")
}

@Composable
fun OnCategoriesLoading(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            )
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



