package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojisUseCase {

    suspend fun getEmojis() :  Flow<Result<List<Emoji>>>
}