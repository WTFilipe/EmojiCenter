package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.model.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchEmojiUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : ISearchEmojiUseCase {
    override suspend fun searchEmojis(search: String): Flow<Result<List<Emoji>>> = flow {

    }
}