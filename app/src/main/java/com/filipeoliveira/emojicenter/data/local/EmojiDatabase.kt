package com.filipeoliveira.emojicenter.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filipeoliveira.emojicenter.data.local.model.CategoryDB
import com.filipeoliveira.emojicenter.data.local.model.EmojiDB

@Database(entities = [CategoryDB::class, EmojiDB::class], version = 1)
abstract class EmojiDatabase : RoomDatabase(){
    abstract fun emojiDao() : EmojiDAO

    abstract fun categoryDao() : CategoryDAO
}