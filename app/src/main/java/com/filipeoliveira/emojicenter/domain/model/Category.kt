package com.filipeoliveira.emojicenter.domain.model

data class Category(
    val slug: String?,
    val subCategories: List<String?>?
)