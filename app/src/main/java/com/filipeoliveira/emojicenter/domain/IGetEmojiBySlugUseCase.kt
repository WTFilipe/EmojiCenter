package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojiBySlugUseCase {

    suspend fun getEmojiBySlug(slug: String) : Flow<Result<Emoji>>
}