package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.data.Category
import com.filipeoliveira.emojicenter.data.IEmojiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor (
    private val repository: IEmojiRepository
): IGetCategoryListUseCase {
    override suspend fun getCategoryList(): Flow<Result<List<Category>>> = flow {

    }
}