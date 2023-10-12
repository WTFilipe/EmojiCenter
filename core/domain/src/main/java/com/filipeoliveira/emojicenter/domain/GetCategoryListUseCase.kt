package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.domain.model.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor (
    private val repository: IEmojiRepository
): IGetCategoryListUseCase {
    override suspend fun getCategoryList(): Flow<Result<List<Category>>> = flow {
        repository.getCategoryList()
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