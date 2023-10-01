package com.filipeoliveira.emojicenter.di

import com.filipeoliveira.emojicenter.domain.IGetEmojisUseCase
import com.filipeoliveira.emojicenter.ui.screens.home.HomeViewModel
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

}