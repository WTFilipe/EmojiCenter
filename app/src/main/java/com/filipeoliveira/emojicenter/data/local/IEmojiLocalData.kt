package com.filipeoliveira.emojicenter.data.local

import com.filipeoliveira.emojicenter.data.local.model.CategoryDB
import com.filipeoliveira.emojicenter.data.local.model.EmojiDB
import kotlinx.coroutines.flow.Flow

interface IEmojiLocalData {

    fun getAllEmojis() : Flow<List<EmojiDB>>
    fun getEmojisByCategory(category: String) : Flow<List<EmojiDB>>
    fun searchEmoji(query: String): Flow<List<EmojiDB>>
    fun getEmojiBySlug(slug: String): Flow<EmojiDB>
    fun insertEmoji(emojiDB: EmojiDB)
    fun insertCategory(categoryDB: CategoryDB)
    fun deleteEmoji(emojiDB: EmojiDB)
    fun getEmojisCategory(): Flow<List<CategoryDB>>
}