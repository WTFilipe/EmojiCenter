package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import kotlinx.coroutines.flow.Flow

interface IGetEmojisUseCase {

    suspend fun getEmojis() :  Flow<Result<List<Emoji>>>
}