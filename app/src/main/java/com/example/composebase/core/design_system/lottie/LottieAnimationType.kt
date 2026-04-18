package com.example.composebase.core.design_system.lottie

import androidx.annotation.RawRes

/**
 * Describes the source used to build a Lottie composition in the UI layer.
 */
sealed class LottieAnimationType {
    /**
     * Loads a Lottie animation bundled in the app as a raw resource.
     */
    data class Raw(@RawRes val resId: Int) : LottieAnimationType()

    /**
     * Loads a Lottie animation from a remote URL.
     */
    data class Url(val value: String) : LottieAnimationType()
}
