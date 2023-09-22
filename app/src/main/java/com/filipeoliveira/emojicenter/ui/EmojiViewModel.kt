package com.filipeoliveira.emojicenter.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.emojicenter.domain.IGetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiByCategoryUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiBySlugUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojisUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class EmojiViewModel(
    private val getCategoryListUseCase: IGetCategoryListUseCase,
    private val getEmojiByCategoryUseCase: IGetEmojiByCategoryUseCase,
    private val getEmojiBySlugUseCase: IGetEmojiBySlugUseCase,
    private val getEmojisUseCase: IGetEmojisUseCase,
) : ViewModel() {

    private var categoryList = MutableStateFlow(UIState.Loading)
    private var emojisByCategory = MutableStateFlow(UIState.Loading)
    private var emojiBySlug = MutableStateFlow(UIState.Loading)
    private var emojiList = MutableStateFlow(UIState.Loading)

    fun getEmojiList(){
        viewModelScope.launch(Dispatchers.IO) {
            getEmojisUseCase.getEmojis().map {

            }
        }
    }
}