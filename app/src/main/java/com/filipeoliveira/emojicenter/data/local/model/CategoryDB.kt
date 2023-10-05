package com.filipeoliveira.emojicenter.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.filipeoliveira.emojicenter.data.model.Category

@Entity
data class CategoryDB(
    @PrimaryKey val slug: String
) {
    fun toCategory() = Category(slug)
}
