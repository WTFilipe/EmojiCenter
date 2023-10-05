package com.filipeoliveira.emojicenter.data

import com.filipeoliveira.emojicenter.data.local.IEmojiLocalData
import com.filipeoliveira.emojicenter.data.remote.IEmojiRemoteData
import com.filipeoliveira.emojicenter.data.model.Category
import com.filipeoliveira.emojicenter.data.model.Emoji
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmojiRepository @Inject constructor(
    private val remoteData: IEmojiRemoteData,
    private val localData: IEmojiLocalData
) : IEmojiRepository{
    override suspend fun getEmojis(): Flow<List<Emoji>> = localData.getAllEmojis().map {
        it.map {emojiDb ->
            emojiDb.toEmoji()
        }
    }
    override suspend fun searchEmoji(search: String): Flow<List<Emoji>> = localData.searchEmoji(search).map {
        it.map {emojiDb ->
            emojiDb.toEmoji()
        }
    }
    override suspend fun getEmojiBySlug(slug: String): Flow<Emoji> = localData.getEmojiBySlug(slug).map {
        it.toEmoji()
    }
    override suspend fun getEmojisByCategory(category: String): Flow<List<Emoji>> = localData.getEmojisByCategory(category).map {
        it.map {emojiDb ->
            emojiDb.toEmoji()
        }
    }
    override suspend fun getCategoryList(): Flow<List<Category>> = localData.getEmojisCategory().map {
        it.map { categoryDB ->
            categoryDB.toCategory()
        }
    }
    override suspend fun refreshEmojis(): Flow<Unit> = flow {
        val emojiList = remoteData.getEmojis()
        for (emoji in emojiList){
            localData.insertEmoji(emoji.toEmojiDb())
        }
    }

    override suspend fun refreshCategories(): Flow<Unit> = flow {
        val categoryList = remoteData.getCategoryList()
        for (category in categoryList){
            localData.insertCategory(category.toCategoryDb())
        }
    }
}