package com.filipeoliveira.emojicenter.ui.screens.search

import com.filipeoliveira.emojicenter.data.model.Emoji

data class SearchScreenModel(
    val categoryAndEmojisList : List<CategoryAndEmojis>,
    val areCategoriesLoading: Boolean,
    val error: Throwable? = null
)

data class CategoryAndEmojis(
    val title: String,
    val isTitleLoading: Boolean,
    val emojis: List<Emoji>,
    val areEmojisLoading: Boolean,
    val emojisError: Throwable? = null
)
