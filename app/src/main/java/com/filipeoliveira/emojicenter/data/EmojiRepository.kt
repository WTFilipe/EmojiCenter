package com.filipeoliveira.emojicenter.data

import com.filipeoliveira.emojicenter.data.remote.IEmojiRemoteData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmojiRepository @Inject constructor(
    private val remoteData: IEmojiRemoteData
) : IEmojiRepository{
    override suspend fun getEmojis(): Flow<List<Emoji>> = remoteData.getEmojis().map { list ->
        list.map {
            it.toEmoji()
        }
    }

    override suspend fun searchEmoji(search: String): Flow<List<Emoji>> = remoteData.searchEmoji(search).map { list ->
        list.map {
            it.toEmoji()
        }
    }

    override suspend fun getEmojiBySlug(slug: String): Flow<Emoji> = remoteData.getEmojiBySlug(slug).map {
        it.toEmoji()
    }

    override suspend fun getCategoryList(): Flow<List<Category>> = remoteData.getCategoryList().map { list ->
        list.map {
            it.toCategory()
        }
    }

    override suspend fun getEmojisByCategory(category: String): Flow<List<Category>> = remoteData.getEmojisByCategory(category).map { list ->
        list.map {
            it.toCategory()
        }
    }
}