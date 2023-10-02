package com.filipeoliveira.emojicenter.ui.screens.search

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.emojicenter.domain.model.Category
import com.filipeoliveira.emojicenter.ui.UIState
import com.filipeoliveira.emojicenter.ui.components.EmojiCategory
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Surface(modifier = modifier) {
        val uiState = viewModel.categoryList.collectAsState().value

        when (uiState) {
            is UIState.Success -> {
                OnSuccess(modifier, uiState.data)
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
fun OnSuccess(modifier: Modifier, data: List<Category>) {

}

@Composable
fun OnError(modifier: Modifier, error: Throwable) {
    TODO("Not yet implemented")
}

@Composable
fun OnLoading(modifier: Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimen16Dp
            )
    ) {
        items(20) {
            EmojiCategory(null, listOf())
        }
    }
}



