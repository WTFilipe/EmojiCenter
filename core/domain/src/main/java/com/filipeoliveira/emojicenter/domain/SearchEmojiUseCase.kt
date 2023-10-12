package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.domain.errors.ShortInputException
import com.filipeoliveira.emojicenter.domain.model.Emoji
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchEmojiUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : ISearchEmojiUseCase {
    override suspend fun searchEmojis(search: String): Flow<Result<List<Emoji>>> = flow {
        when {
            search.length < 3 -> emit(Result.Error(ShortInputException()))
            else -> {
                repository.searchEmoji(search)
                    .catch {
                        emit(Result.Error(it))
                    }
                    .collect { list ->
                        if (list.isEmpty()) {
                            emit(Result.Error(EmptyResponseException()))
                        } else {
                            emit(Result.Success(list))
                        }
                    }
            }
        }
    }
}