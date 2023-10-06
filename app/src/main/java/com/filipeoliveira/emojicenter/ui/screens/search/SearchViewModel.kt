package com.filipeoliveira.emojicenter.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.filipeoliveira.emojicenter.domain.IGetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiByCategoryUseCase
import com.filipeoliveira.emojicenter.domain.IRefreshCategoriesUseCase
import com.filipeoliveira.emojicenter.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCategoryListUseCase: IGetCategoryListUseCase,
    private val getEmojiByCategoryUseCase: IGetEmojiByCategoryUseCase,
    private val refreshCategoriesUseCase: IRefreshCategoriesUseCase,
) : ViewModel(), ISearchViewModel{

    private var _categoryAndEmojis = MutableStateFlow(
        SearchScreenModel(
            categoryAndEmojisList = listOf(),
            areCategoriesLoading = true
        )
    )
    val categoryAndEmojis: StateFlow<SearchScreenModel>
        get() = _categoryAndEmojis

    init {
        this.refreshCategories()
        this.getCategoryList()
    }
    override fun refreshCategories(){
        viewModelScope.launch(Dispatchers.IO) {
            _categoryAndEmojis.value = _categoryAndEmojis.value.copy(
                areCategoriesLoading = true
            )
            refreshCategoriesUseCase.refreshCategories()
                .catch {
                    _categoryAndEmojis.value = _categoryAndEmojis.value.copy(
                        error = it
                    )
                }
                .collect {
                    _categoryAndEmojis.value = _categoryAndEmojis.value.copy(
                        areCategoriesLoading = false
                    )
                }
        }
    }
    override fun getCategoryList() {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryAndEmojis.value = SearchScreenModel(
                categoryAndEmojisList = listOf(),
                areCategoriesLoading = true
            )

            getCategoryListUseCase.getCategoryList()
                .catch {
                    _categoryAndEmojis.value = SearchScreenModel(
                        categoryAndEmojisList = listOf(),
                        areCategoriesLoading = false,
                        error = it
                    )
                }
                .collect { list ->
                    when (list) {
                        is Result.Success -> {
                            _categoryAndEmojis.value = SearchScreenModel(
                                categoryAndEmojisList = list.data.map {
                                    CategoryAndEmojis(
                                        title = it.slug ?: "",
                                        emojis = listOf(),
                                        isTitleLoading = false,
                                        areEmojisLoading = true
                                    )
                                },
                                areCategoriesLoading = false
                            )

                            for (category in list.data){
                                category.slug?.takeIf { it.isNotBlank() }?.let {
                                    getEmojisByCategory(it)
                                }
                            }

                        }

                        is Result.Error -> _categoryAndEmojis.value = SearchScreenModel(
                            categoryAndEmojisList = listOf(),
                            areCategoriesLoading = false,
                            error = list.error
                        )
                    }
                }
        }
    }
    override fun getEmojisByCategory(slug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _categoryAndEmojis.value = categoryAndEmojis.value.copy(
                categoryAndEmojisList = categoryAndEmojis.value.categoryAndEmojisList.map {
                    it.copy(
                        areEmojisLoading = true
                    )
                }
            )
            getEmojiByCategoryUseCase.getEmojisByCategory(slug)
                .catch { error ->
                    _categoryAndEmojis.value = categoryAndEmojis.value.copy(
                        categoryAndEmojisList = categoryAndEmojis.value.categoryAndEmojisList.map {
                            if (it.title == slug) {
                                it.copy(
                                    emojisError = error
                                )
                            } else {
                                it.copy()
                            }
                        }
                    )
                }
                .collect { result ->
                    when (result) {
                        is Result.Success -> _categoryAndEmojis.value =
                            categoryAndEmojis.value.copy(
                                categoryAndEmojisList = categoryAndEmojis.value.categoryAndEmojisList.map {
                                    if (it.title == slug) {
                                        it.copy(
                                            emojis = result.data,
                                            areEmojisLoading = false
                                        )
                                    } else {
                                        it.copy()
                                    }
                                },
                            )

                        is Result.Error -> _categoryAndEmojis.value = categoryAndEmojis.value.copy(
                            categoryAndEmojisList = categoryAndEmojis.value.categoryAndEmojisList.map {
                                if (it.title == slug) {
                                    it.copy(
                                        emojisError = result.error,
                                        areEmojisLoading = false
                                    )
                                } else {
                                    it.copy()
                                }
                            }
                        )
                    }
                }
        }
    }
}