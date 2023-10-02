package com.filipeoliveira.emojicenter.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.emojicenter.domain.model.Category
import com.filipeoliveira.emojicenter.domain.IGetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiByCategoryUseCase
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
class SearchViewModel @Inject constructor (
    private val getCategoryListUseCase: IGetCategoryListUseCase,
    private val getEmojiByCategoryUseCase: IGetEmojiByCategoryUseCase,
) : ViewModel() {

    private var _categoryList = MutableStateFlow<UIState<List<Category>>>(UIState.Loading)
    val categoryList : StateFlow<UIState<List<Category>>>
        get() = _categoryList

    init {
        this.getCategoryList()
    }

    private fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryList.value = UIState.Loading
            getCategoryListUseCase.getCategoryList()
                .catch {
                    _categoryList.value = UIState.Error(it)
                }
                .collect { list ->
                    when (list) {
                        is Result.Success -> _categoryList.value = UIState.Success(list.data)
                        is Result.Error -> _categoryList.value = UIState.Error(list.error)
                    }
                }
        }
    }
}