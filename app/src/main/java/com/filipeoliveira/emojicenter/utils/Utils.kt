package com.filipeoliveira.emojicenter.utils

import com.filipeoliveira.emojicenter.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T: Any> Flow<T>.executeOrEmitErrors(action: suspend (T) -> Unit): Flow<Result<T>> {
    return flow {
        try {
            collect { item ->
                action(item)
            }
        } catch (e: Exception){
            emit(Result.Error(e))
        }
    }
}