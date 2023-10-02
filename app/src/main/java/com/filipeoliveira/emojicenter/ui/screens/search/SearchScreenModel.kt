package com.filipeoliveira.emojicenter.ui.screens.search

import com.filipeoliveira.emojicenter.domain.model.Emoji

data class SearchScreenModel(
    val categoriesAndEmojisList : List<CategoriesAndEmojis>
)

data class CategoriesAndEmojis(
    val title: String?,
    val emojis: List<Emoji>
)
