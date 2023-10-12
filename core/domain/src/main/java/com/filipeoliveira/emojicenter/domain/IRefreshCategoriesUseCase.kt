package com.filipeoliveira.emojicenter.domain

import kotlinx.coroutines.flow.Flow

interface IRefreshCategoriesUseCase {
    suspend fun refreshCategories() : Flow<Result<Unit>>
}