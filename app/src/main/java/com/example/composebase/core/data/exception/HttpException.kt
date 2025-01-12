package com.example.composebase.core.data.exception

import com.example.composebase.core.model.DataError

class HttpException(
    val errorType: DataError.Network,
    override val message: String?
) : Exception(message)