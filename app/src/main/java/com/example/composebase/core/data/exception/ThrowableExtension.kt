package com.example.composebase.core.data.exception

import androidx.annotation.StringRes
import com.example.composebase.R
import com.example.composebase.core.EMPTY_CHARACTER
import com.example.composebase.core.model.DataError


sealed class ErrorMessage {
    data class Resource(@StringRes val resId: Int) : ErrorMessage()
    data class Text(val text: String) : ErrorMessage()
}

fun Throwable.getErrorMessage(): Pair<Int, ErrorMessage> {
    return when (this) {
        is HttpException -> {
            return when (this.errorType) {
                DataError.Network.REQUEST_TIMEOUT -> Pair(
                    R.string.network_error_request_timeout,
                    ErrorMessage.Resource(R.string.network_error_request_timeout_action)
                )

                DataError.Network.SERVER_ERROR -> Pair(
                    R.string.network_error_server,
                    ErrorMessage.Resource(R.string.network_error_server_action)
                )

                DataError.Network.LOGIC_ERROR -> Pair(
                    R.string.network_safe_request_error,
                    ErrorMessage.Text(this.message ?: EMPTY_CHARACTER)
                )

                DataError.Network.UNKNOWN -> Pair(
                    R.string.network_error_network,
                    ErrorMessage.Resource(R.string.network_error_network_action)
                )

                DataError.Network.FORBIDDEN -> Pair(
                    R.string.network_error_forbidden,
                    ErrorMessage.Resource(R.string.network_error_forbidden_action)
                )

                DataError.Network.BAD_REQUEST -> Pair(
                    R.string.network_error_bad_request,
                    ErrorMessage.Text(this.message ?: EMPTY_CHARACTER)
                )

                DataError.Network.NOT_FOUND -> Pair(
                    R.string.network_error_not_found,
                    ErrorMessage.Resource(R.string.network_error_not_found_action)
                )

                DataError.Network.UNAUTHORIZED -> Pair(
                    R.string.network_error_unauthorized,
                    ErrorMessage.Resource(R.string.network_error_unauthorized_action)
                )
            }
        }


        else -> Pair(
            R.string.local_unexpected_error_message,
            ErrorMessage.Resource(R.string.local_unexpected_error_description_message)
        )
    }
}