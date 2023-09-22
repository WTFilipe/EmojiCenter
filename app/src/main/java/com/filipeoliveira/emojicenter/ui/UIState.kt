package com.filipeoliveira.emojicenter.ui

sealed interface UIState<out T: Any> {
    data class Success<out T: Any>(val data: T) : UIState<T>
    data class Error(val errors: List<Throwable>) : UIState<Nothing>
    object Loading: UIState<Nothing>
}