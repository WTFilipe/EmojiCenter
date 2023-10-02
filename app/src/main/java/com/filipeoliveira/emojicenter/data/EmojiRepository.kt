package com.filipeoliveira.emojicenter.data

import com.filipeoliveira.emojicenter.data.remote.IEmojiRemoteData
import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import com.filipeoliveira.emojicenter.domain.model.Category
import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class EmojiRepository @Inject constructor(
    private val remoteData: IEmojiRemoteData
) : IEmojiRepository{
    override suspend fun getEmojis(): Flow<List<EmojiRemote>> = remoteData.getEmojis()
    override suspend fun searchEmoji(search: String): Flow<List<EmojiRemote>> = remoteData.searchEmoji(search)
    override suspend fun getEmojiBySlug(slug: String): Flow<EmojiRemote> = remoteData.getEmojiBySlug(slug)
    override suspend fun getCategoryList(): Flow<List<CategoryRemote>> = remoteData.getCategoryList()
    override suspend fun getEmojisByCategory(category: String): Flow<List<EmojiRemote>> = remoteData.getEmojisByCategory(category)
}