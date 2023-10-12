package com.filipeoliveira.emojicenter.ui.screens.search

import com.filipeoliveira.emojicenter.domain.model.Emoji


data class SearchScreenModel(
    val categoryAndEmojisList : List<CategoryAndEmojis> = emptyList(),
    val searchResultList : List<Emoji> = emptyList(),
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
