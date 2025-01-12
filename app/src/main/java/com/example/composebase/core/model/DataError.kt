package com.example.composebase.core.model


sealed interface Error

interface DataError : Error {
    enum class Network: DataError {
        REQUEST_TIMEOUT,
        SERVER_ERROR,
        LOGIC_ERROR,
        UNKNOWN,
        FORBIDDEN,
        BAD_REQUEST,
        NOT_FOUND,
        UNAUTHORIZED
    }
}