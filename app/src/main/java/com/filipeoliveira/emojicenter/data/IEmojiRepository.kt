package com.filipeoliveira.emojicenter.data

import com.filipeoliveira.emojicenter.data.remote.model.CategoryRemote
import com.filipeoliveira.emojicenter.data.remote.model.EmojiRemote
import com.filipeoliveira.emojicenter.domain.model.Category
import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IEmojiRepository {
    suspend fun getEmojis() : Flow<List<EmojiRemote>>
    suspend fun searchEmoji(search: String) : Flow<List<EmojiRemote>>
    suspend fun getEmojiBySlug(slug: String) : Flow<EmojiRemote>
    suspend fun getCategoryList() : Flow<List<CategoryRemote>>
    suspend fun getEmojisByCategory(category: String) : Flow<List<EmojiRemote>>
}