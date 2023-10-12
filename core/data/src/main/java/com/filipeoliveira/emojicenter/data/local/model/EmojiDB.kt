package com.filipeoliveira.emojicenter.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.emojicenter.domain.model.Emoji

@Entity
data class EmojiDB(
    @PrimaryKey val slug: String,
    @ColumnInfo(name = "unicode_name") val unicodeName: String,
    @ColumnInfo(name = "character") val character: String,
    @ColumnInfo(name = "category") val subgroup: String,
    @ColumnInfo(name = "code_point") val codePoint: String,
    @ColumnInfo(name = "group") val group: String,
) {
    fun toEmoji() = Emoji(
        character,
        codePoint,
        group,
        slug,
        subgroup,
        unicodeName
    )
}
