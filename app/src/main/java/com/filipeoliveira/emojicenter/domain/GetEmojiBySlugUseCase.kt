package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.utils.executeOrEmitErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojiBySlugUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : IGetEmojiBySlugUseCase {
    override suspend fun getEmojiBySlug(slug: String): Flow<Result<Emoji>> = flow {
        repository.getEmojiBySlug(slug).executeOrEmitErrors { emoji: Emoji ->
            emit(Result.Success(emoji))
        }
    }
}