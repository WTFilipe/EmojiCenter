package com.filipeoliveira.emojicenter

import com.filipeoliveira.emojicenter.data.EmojiRepository
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.data.remote.EmojiRemoteData
import com.filipeoliveira.emojicenter.data.remote.EmojiService
import com.filipeoliveira.emojicenter.data.remote.IEmojiRemoteData
import com.filipeoliveira.emojicenter.data.remote.RetrofitConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideEmojiService() : EmojiService {
        return RetrofitConfig.service
    }

    @Provides
    @Singleton
    fun providesEmojiRemoteData(service: EmojiService) : IEmojiRemoteData {
        return EmojiRemoteData(service)
    }

    @Provides
    @Singleton
    fun providesEmojiRepository(remoteData: IEmojiRemoteData) : IEmojiRepository {
        return EmojiRepository(remoteData)
    }
}