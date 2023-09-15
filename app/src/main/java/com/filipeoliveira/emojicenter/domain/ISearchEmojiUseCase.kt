package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import kotlinx.coroutines.flow.Flow

interface ISearchEmojiUseCase {

    suspend fun searchEmojis(search: String) : Flow<Result<List<Emoji>>>

}