package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojiByCategoryUseCase @Inject constructor(
    private val repository: IEmojiRepository
): IGetEmojiByCategoryUseCase {
    override suspend fun getEmojisByCategory(category: String) : Flow<Result<List<Emoji>>> = flow {
        repository.getEmojisByCategory(category)
            .catch {
                emit(Result.Error(it))
            }
            .collect{list ->
                val mappedList = list.map { it.toEmoji() }
                if (list.isEmpty()) {
                    emit(Result.Error(EmptyResponseException()))
                } else {
                    emit(Result.Success(mappedList))
                }
            }
    }
}