package com.filipeoliveira.emojicenter.domain

import kotlinx.coroutines.flow.Flow

interface IRefreshEmojiUseCase {
    suspend fun refreshEmojis() : Flow<Result<Unit>>
}