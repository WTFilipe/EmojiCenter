package com.filipeoliveira.emojicenter.di

import com.filipeoliveira.emojicenter.ui.screens.home.HomeViewModel
import com.filipeoliveira.emojicenter.ui.screens.home.IHomeViewModel
import com.filipeoliveira.emojicenter.ui.screens.search.ISearchViewModel
import com.filipeoliveira.emojicenter.ui.screens.search.SearchViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun providesEmojiViewModel(
        homeViewModel: HomeViewModel
    ): IHomeViewModel

    @Binds
    abstract fun providesSearchViewModel(
        searchViewModel: SearchViewModel
    ): ISearchViewModel
}