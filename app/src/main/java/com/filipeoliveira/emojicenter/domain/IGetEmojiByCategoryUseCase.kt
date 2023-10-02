package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojiByCategoryUseCase {
    suspend fun getEmojisByCategory(category: String) : Flow<Result<List<Emoji>>>

}