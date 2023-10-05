package com.filipeoliveira.emojicenter.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.filipeoliveira.emojicenter.data.local.model.EmojiDB
import kotlinx.coroutines.flow.Flow

@Dao
interface EmojiDAO {
    @Query("SELECT * FROM emojidb")
    fun getAllEmojis() : Flow<List<EmojiDB>>

    @Query("SELECT * FROM emojidb WHERE `group` LIKE :category")
    fun getEmojisByCategory(category: String) : Flow<List<EmojiDB>>

    @Query("SELECT * from emojidb WHERE slug LIKE :slug")
    fun getEmojiBySlug(slug: String): Flow<EmojiDB>

    @Query("SELECT * " +
            "FROM emojidb " +
            "WHERE " +
            " slug LIKE '%' || :query || '%' OR" +
            " unicode_name LIKE '%' || :query || '%' OR" +
            " character LIKE '%' || :query || '%'")
    fun searchEmoji(query: String): Flow<List<EmojiDB>>

    @Insert
    fun insertAll(emojiDB: EmojiDB)

    @Delete
    fun delete(emojiDB: EmojiDB)
}