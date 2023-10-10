package com.filipeoliveira.emojicenter.ui.screens.search

interface ISearchViewModel{

    fun refreshCategories()

    fun getCategoryList()

    fun getEmojisByCategory(slug: String)

    fun searchEmojis(query: String)
}