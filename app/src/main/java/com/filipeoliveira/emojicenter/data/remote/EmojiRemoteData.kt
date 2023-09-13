package com.filipeoliveira.emojicenter.data.remote

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class EmojiRemoteData @Inject constructor (
    private val service: EmojiService
) : IEmojiRemoteData {
    override suspend fun getEmojis(): Flow<List<EmojiRemote>> = flow {
        val emojiList = service.getAllEmojis()
        emit(emojiList)
    }

    override suspend fun searchEmoji(search: String): Flow<List<EmojiRemote>> = flow {
        val searchEmoji = service.searchEmoji(search)
        emit(searchEmoji)
    }

    override suspend fun getEmojiBySlug(slug: String): Flow<EmojiRemote> = flow {
        val emoji = service.getEmojiBySlug(slug)
        emit(emoji)
    }

    override suspend fun getCategoryList(): Flow<List<CategoryRemote>> = flow {
        val categories = service.getCategoryList()
        emit(categories)
    }

    override suspend fun getEmojisByCategory(category: String): Flow<List<CategoryRemote>> = flow {
        val emojisByCategory = service.getEmojisByCategory(category)
        emit(emojisByCategory)
    }
}