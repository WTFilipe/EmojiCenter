package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojiBySlugUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : IGetEmojiBySlugUseCase {
    override suspend fun getEmojiBySlug(slug: String): Flow<Result<Emoji>> = flow {

    }
}