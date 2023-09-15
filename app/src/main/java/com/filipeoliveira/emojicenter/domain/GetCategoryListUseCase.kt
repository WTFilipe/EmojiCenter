package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Category
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import com.filipeoliveira.emojicenter.domain.Result
import com.filipeoliveira.emojicenter.domain.errors.EmptyResponseException
import com.filipeoliveira.emojicenter.utils.executeOrEmitErrors
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor (
    private val repository: IEmojiRepository
): IGetCategoryListUseCase {
    override suspend fun getCategoryList(): Flow<Result<List<Category>>> = flow {
        repository.getCategoryList().executeOrEmitErrors { list ->
            if (list.isEmpty()){
                emit(Result.Error(EmptyResponseException()))
            } else {
                emit(Result.Success(list))
            }
        }
    }
}