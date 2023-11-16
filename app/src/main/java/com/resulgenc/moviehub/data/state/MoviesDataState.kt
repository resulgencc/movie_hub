package com.resulgenc.moviehub.data.state

sealed class MoviesDataState<out T> {

    data class Success<out T>(val data: T) : MoviesDataState<T>()

    data class Error(val exception: Exception) : MoviesDataState<Nothing>()

    data object Loading : MoviesDataState<Nothing>()

}
