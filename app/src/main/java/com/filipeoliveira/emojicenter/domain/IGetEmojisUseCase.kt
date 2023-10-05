package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.model.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojisUseCase {

    suspend fun getEmojis() :  Flow<Result<List<Emoji>>>
}