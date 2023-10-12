package com.filipeoliveira.emojicenter.ui.screens.search

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.filipeoliveira.emojicenter.R
import com.filipeoliveira.emojicenter.domain.model.Emoji
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.domain.errors.ShortInputException
import com.filipeoliveira.emojicenter.ui.components.EmojiCategory
import com.filipeoliveira.emojicenter.ui.components.EmojiItemRightLayout
import com.filipeoliveira.emojicenter.ui.components.EmojiSearchBar
import com.filipeoliveira.emojicenter.ui.components.EmojiText
import com.filipeoliveira.emojicenter.ui.theme.dimen16Dp
import com.filipeoliveira.emojicenter.ui.theme.dimen8Dp

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        SearchScreenSearchBar(viewModel = viewModel)
        SearchScreenContent(viewModel)
    }
}

@Composable
fun SearchScreenSearchBar(modifier: Modifier = Modifier, viewModel: SearchViewModel) {
    var searchFieldValue by rememberSaveable{
        mutableStateOf("")
    }
    EmojiSearchBar(
        value = searchFieldValue,
        modifier = modifier
            .padding(start = dimen16Dp, top = dimen16Dp, end = dimen16Dp)
    ) { query ->
        searchFieldValue = query
        viewModel.searchEmojis(query)
    }
}

@Composable
private fun SearchScreenContent(viewModel: SearchViewModel) {
    val uiState = viewModel.categoryAndEmojis.collectAsState().value

    when {
        uiState.areCategoriesLoading -> OnCategoriesLoading()
        uiState.error != null -> OnCategoriesError(uiState.error)
        uiState.categoryAndEmojisList.isNotEmpty() -> OnCategoriesSuccess(uiState.categoryAndEmojisList)
        uiState.searchResultList.isNotEmpty() -> OnSearchResultSuccess(uiState.searchResultList)
    }
}

@Composable
fun OnSearchResultSuccess(data: List<Emoji>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = dimen16Dp)
    ) {
        items(data.size) {
            EmojiItemRightLayout(data[it]) {
                clipboardManager.setText(AnnotatedString(it))
                Toast.makeText(context, context.getString(R.string.emoji_copied_to_clipboard, it), Toast.LENGTH_SHORT).show()
            }
            Spacer(modifier = Modifier.height(dimen8Dp))
        }
    }
}

@Composable
fun OnCategoriesSuccess(data: List<CategoryAndEmojis>, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = dimen16Dp)
    ) {
        items(data.size) {
            EmojiCategory(data[it]) {
                clipboardManager.setText(AnnotatedString(it))
                Toast.makeText(context, context.getString(R.string.emoji_copied_to_clipboard, it), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun OnCategoriesError(error: Throwable, modifier: Modifier = Modifier) {
    val errorFeedback = when(error){
        is ShortInputException -> stringResource(R.string.short_input_feedback)
        is EmptyResponseException -> stringResource(R.string.empty_response)
        else -> stringResource(R.string.generic_error)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(dimen16Dp)
    ) {
        EmojiText(
            text = errorFeedback,
            textAlign = TextAlign.Center
        )
    }
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