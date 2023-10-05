package com.filipeoliveira.emojicenter.data.local

import com.filipeoliveira.emojicenter.data.local.model.CategoryDB
import com.filipeoliveira.emojicenter.data.local.model.EmojiDB
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmojiLocalData @Inject constructor (
    private val database: EmojiDatabase
) : IEmojiLocalData {

    override fun getAllEmojis(): Flow<List<EmojiDB>> = database.emojiDao().getAllEmojis()

    override fun getEmojisByCategory(category: String): Flow<List<EmojiDB>> = database.emojiDao().getEmojisByCategory(category)

    override fun searchEmoji(query: String): Flow<List<EmojiDB>> = database.emojiDao().searchEmoji(query)

    override fun getEmojiBySlug(slug: String): Flow<EmojiDB> = database.emojiDao().getEmojiBySlug(slug)

    override fun getEmojisCategory(): Flow<List<CategoryDB>> = database.categoryDao().getAllCategories()

    override fun insertEmoji(emojiDB: EmojiDB) {
       database.emojiDao().insertAll(emojiDB)
    }

    override fun insertCategory(categoryDB: CategoryDB) {
        database.categoryDao().insertAll(categoryDB)
    }

    override fun deleteEmoji(emojiDB: EmojiDB) {
        database.emojiDao().delete(emojiDB)
    }
}