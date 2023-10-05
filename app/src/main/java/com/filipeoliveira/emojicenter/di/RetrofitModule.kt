package com.filipeoliveira.emojicenter.di

import com.filipeoliveira.emojicenter.data.remote.EmojiService
import com.filipeoliveira.emojicenter.data.remote.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    @Provides
    @Singleton
    fun provideEmojiService() : EmojiService {
        return RetrofitConfig.service
    }
}