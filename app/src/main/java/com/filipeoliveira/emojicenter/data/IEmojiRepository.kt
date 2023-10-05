package com.filipeoliveira.emojicenter.data

import com.filipeoliveira.emojicenter.data.model.Category
import com.filipeoliveira.emojicenter.data.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IEmojiRepository {
    suspend fun getEmojis() : Flow<List<Emoji>>
    suspend fun searchEmoji(search: String) : Flow<List<Emoji>>
    suspend fun getEmojiBySlug(slug: String) : Flow<Emoji>
    suspend fun getCategoryList() : Flow<List<Category>>
    suspend fun getEmojisByCategory(category: String) : Flow<List<Emoji>>
    suspend fun refreshEmojis() : Flow<Unit>
    suspend fun refreshCategories() : Flow<Unit>

}