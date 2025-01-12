package com.example.composebase.core.data

import com.example.composebase.core.EMPTY_CHARACTER


sealed class UIStateStatus<out T> {

    data class Loading<T>(val message: String = EMPTY_CHARACTER) : UIStateStatus<T>()

    data class Success<out T>(val data: T) : UIStateStatus<T>()

    data class Error<out T>(
        val exception: Throwable,
    ) : UIStateStatus<T>()

    data class Empty<out T>(val emptyData: T) : UIStateStatus<T>()

}