package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojiBySlugUseCase {

    suspend fun getEmojiBySlug(slug: String) : Flow<Result<Emoji>>
}