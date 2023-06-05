package com.jaino.common.model

sealed class UiState<out T> {
    object Init : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val message: String?) : UiState<Nothing>()
}

sealed class UiEvent<out T> {
    data class Success<T>(val data: T) : UiEvent<T>()
    data class Failure(val error: Throwable) : UiEvent<Nothing>()
}