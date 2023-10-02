package com.filipeoliveira.emojicenter.di

import com.filipeoliveira.emojicenter.domain.IGetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiByCategoryUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojisUseCase
import com.filipeoliveira.emojicenter.ui.screens.home.HomeViewModel
import com.filipeoliveira.emojicenter.ui.screens.search.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun providesEmojiViewModel(getEmojisUseCase: IGetEmojisUseCase) : HomeViewModel {
        return HomeViewModel(getEmojisUseCase)
    }

    @Provides
    fun providesSearchViewModel(getCategoryListUseCase: IGetCategoryListUseCase, getEmojiByCategoryUseCase: IGetEmojiByCategoryUseCase) : SearchViewModel {
        return SearchViewModel(getCategoryListUseCase, getEmojiByCategoryUseCase)
    }

}