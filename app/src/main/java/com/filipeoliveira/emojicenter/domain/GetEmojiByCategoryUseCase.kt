package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.utils.executeOrEmitErrors
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojiByCategoryUseCase @Inject constructor(
    private val repository: IEmojiRepository
): IGetEmojiByCategoryUseCase {
    override suspend fun getEmojisByCategory(category: String) = flow {
        repository.getEmojisByCategory(category = category).executeOrEmitErrors { list ->
            emit(Result.Success(list))
        }
    }
}