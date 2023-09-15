package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.utils.executeOrEmitErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojisUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : IGetEmojisUseCase{
    override suspend fun getEmojis(): Flow<Result<List<Emoji>>> = flow {
        repository.getEmojis().executeOrEmitErrors { list ->
            if (list.isEmpty()){
                emit(Result.Error(EmptyResponseException()))
            } else {
                emit(Result.Success(list))
            }
        }
    }
}