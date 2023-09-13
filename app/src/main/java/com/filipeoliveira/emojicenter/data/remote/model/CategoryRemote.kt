package com.filipeoliveira.emojicenter.data.remote.model

import com.filipeoliveira.emojicenter.data.Category

data class CategoryRemote(
    val slug: String?,
    val subCategories: List<String?>?
) {
    fun toCategory() = Category(slug, subCategories)
}