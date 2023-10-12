package com.filipeoliveira.emojicenter.data.di

import android.content.Context
import androidx.room.Room
import com.filipeoliveira.emojicenter.data.local.EmojiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun providesEmojiDatabase(@ApplicationContext applicationContext: Context) : EmojiDatabase {
        return Room
            .databaseBuilder(context = applicationContext, EmojiDatabase::class.java, "emoji-database")
            .build()
    }
}