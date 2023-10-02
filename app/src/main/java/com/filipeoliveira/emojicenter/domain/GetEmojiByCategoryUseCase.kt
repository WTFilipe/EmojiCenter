package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Emoji
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetEmojiByCategoryUseCase @Inject constructor(
    private val repository: IEmojiRepository
): IGetEmojiByCategoryUseCase {
    override suspend fun getEmojisByCategory(category: String) : Flow<Result<List<Emoji>>> = flow {

    }
}