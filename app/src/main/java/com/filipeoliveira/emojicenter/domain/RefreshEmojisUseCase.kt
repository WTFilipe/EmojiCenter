package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshEmojisUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : IRefreshEmojiUseCase {
     override suspend fun refreshEmojis(): Flow<Result<Unit>> = flow{
        repository.refreshEmojis()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                emit(Result.Success(Unit))
            }
    }
}