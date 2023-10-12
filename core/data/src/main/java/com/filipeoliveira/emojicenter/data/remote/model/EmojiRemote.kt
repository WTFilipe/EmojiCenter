package com.filipeoliveira.emojicenter.data.remote.model

import com.filipeoliveira.emojicenter.data.local.model.EmojiDB

data class EmojiRemote(
    val character: String?,
    val codePoint: String?,
    val group: String?,
    val slug: String?,
    val subGroup: String?,
    val unicodeName: String?
) {
    fun toEmojiDb() = EmojiDB(
        slug ?: "",
        unicodeName ?: "",
        character ?: "",
        subGroup ?: "",
        codePoint ?: "",
        group ?: "")
}