package com.filipeoliveira.emojicenter.data.model

data class Emoji(
    val character: String?,
    val codePoint: String? = null,
    val group: String? = null,
    val slug: String?,
    val subGroup: String? = null,
    val unicodeName: String?
)