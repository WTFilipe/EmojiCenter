package com.filipeoliveira.emojicenter.domain

import com.filipeoliveira.emojicenter.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface IGetCategoryListUseCase {
    suspend fun getCategoryList() : Flow<Result<List<Category>>>
}