package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RefreshCategoriesUseCase @Inject constructor(
    private val repository: IEmojiRepository
) : IRefreshCategoriesUseCase {
     override suspend fun refreshCategories(): Flow<Result<Unit>> = flow{
        repository.refreshCategories()
            .catch {
                emit(Result.Error(it))
            }
            .collect{
                emit(Result.Success(Unit))
            }
    }
}