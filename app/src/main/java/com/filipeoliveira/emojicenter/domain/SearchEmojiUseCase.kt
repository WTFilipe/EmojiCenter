package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.utils.executeOrEmitErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchEmojiUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : ISearchEmojiUseCase {
    override suspend fun searchEmojis(search: String): Flow<Result<List<Emoji>>> = flow {
        repository.searchEmoji(search).executeOrEmitErrors { list ->
            if (list.isEmpty()){
                emit(Result.Error(EmptyResponseException()))
            } else {
                emit(Result.Success(list))
            }
        }
    }
}