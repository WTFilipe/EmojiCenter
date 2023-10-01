package com.filipeoliveira.emojicenter.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.emojicenter.data.Emoji
import com.filipeoliveira.emojicenter.domain.IGetEmojisUseCase
import com.filipeoliveira.emojicenter.domain.Result
import com.filipeoliveira.emojicenter.ui.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (
    private val getEmojisUseCase: IGetEmojisUseCase,
) : ViewModel() {

    private var _emojiList = MutableStateFlow<UIState<List<Emoji>>>(UIState.Loading)
    val emojiList : StateFlow<UIState<List<Emoji>>>
        get() = _emojiList

    init {
        this.getEmojiList()
    }

    private fun getEmojiList() {
        viewModelScope.launch(Dispatchers.IO) {
            _emojiList.value = UIState.Loading
            getEmojisUseCase.getEmojis()
                .catch {
                    _emojiList.value = UIState.Error(it)
                }
                .collect { list ->
                    when (list) {
                        is Result.Success -> _emojiList.value = UIState.Success(list.data)
                        is Result.Error -> _emojiList.value = UIState.Error(list.error)
                    }
                }
        }
    }
}