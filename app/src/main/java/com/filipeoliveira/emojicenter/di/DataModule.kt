package com.filipeoliveira.emojicenter.di

import com.filipeoliveira.emojicenter.data.EmojiRepository
import com.filipeoliveira.emojicenter.data.local.EmojiLocalData
import com.filipeoliveira.emojicenter.data.local.IEmojiLocalData
import com.filipeoliveira.emojicenter.data.remote.EmojiRemoteData
import com.filipeoliveira.emojicenter.data.remote.IEmojiRemoteData
import com.filipeoliveira.emojicenter.domain.GetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.GetEmojiByCategoryUseCase
import com.filipeoliveira.emojicenter.domain.GetEmojisUseCase
import com.filipeoliveira.emojicenter.domain.IEmojiRepository
import com.filipeoliveira.emojicenter.domain.IGetCategoryListUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojiByCategoryUseCase
import com.filipeoliveira.emojicenter.domain.IGetEmojisUseCase
import com.filipeoliveira.emojicenter.domain.IRefreshCategoriesUseCase
import com.filipeoliveira.emojicenter.domain.IRefreshEmojiUseCase
import com.filipeoliveira.emojicenter.domain.ISearchEmojiUseCase
import com.filipeoliveira.emojicenter.domain.RefreshCategoriesUseCase
import com.filipeoliveira.emojicenter.domain.RefreshEmojisUseCase
import com.filipeoliveira.emojicenter.domain.SearchEmojiUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun providesEmojiRemoteData(remoteData: EmojiRemoteData) : IEmojiRemoteData
    @Binds
    abstract fun providesEmojiLocalData(localData: EmojiLocalData): IEmojiLocalData
    @Binds
    abstract fun providesEmojiRepository(repository: EmojiRepository) : IEmojiRepository
    @Binds
    abstract fun providesGetEmojiUseCase(getEmojisUseCase: GetEmojisUseCase) : IGetEmojisUseCase
    @Binds
    abstract fun providesGetCategoryListUseCase(getCategoryListUseCase: GetCategoryListUseCase) : IGetCategoryListUseCase
    @Binds
    abstract fun providesGetEmojisByCategoryUseCase(getEmojiByCategoryUseCase: GetEmojiByCategoryUseCase) : IGetEmojiByCategoryUseCase
    @Binds
    abstract fun providesRefreshEmojisUseCase(refreshEmojisUseCase: RefreshEmojisUseCase) : IRefreshEmojiUseCase
    @Binds
    abstract fun providesRefreshCategoriesUseCase(refreshCategoriesUseCase: RefreshCategoriesUseCase) : IRefreshCategoriesUseCase
    @Binds
    abstract fun providesSearchEmojisUseCase(searchEmojiUseCase: SearchEmojiUseCase) : ISearchEmojiUseCase
}