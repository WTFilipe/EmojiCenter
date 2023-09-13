package com.filipeoliveira.emojicenter.data.remote.model

import com.filipeoliveira.emojicenter.data.Emoji

data class EmojiRemote(
    val character: String?,
    val codePoint: String?,
    val group: String?,
    val slug: String?,
    val subGroup: String?,
    val unicodeName: String?
) {
    fun toEmoji() = Emoji(character, codePoint, group, slug, subGroup, unicodeName)
}