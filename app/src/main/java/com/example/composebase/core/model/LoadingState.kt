package com.example.composebase.core.model

import androidx.annotation.StringRes
import com.example.composebase.core.ZERO_INTEGER

data class LoadingState(
    val isLoading: Boolean = false,
    @StringRes val message: Int? = ZERO_INTEGER
)