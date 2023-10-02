package com.filipeoliveira.emojicenter.data.remote.model

import com.filipeoliveira.emojicenter.domain.model.Category

data class CategoryRemote(
    val slug: String?,
    val subCategories: List<String?>?
) {
    fun toCategory() = Category(slug, subCategories)
}