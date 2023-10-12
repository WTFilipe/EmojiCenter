package com.filipeoliveira.emojicenter.data.remote.model

import com.filipeoliveira.emojicenter.data.local.model.CategoryDB

data class CategoryRemote(
    val slug: String?,
) {
    fun toCategoryDb() = CategoryDB(slug ?: "")
}