package com.ba.pokedex.webservice.utils.network

import android.annotation.SuppressLint
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.logging.Logger

object LoggingInterceptor : Interceptor {
    private val logger = Logger.getLogger(LoggingInterceptor::class.java.name)

    @SuppressLint("DefaultLocale")
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val t1 = System.nanoTime()
        logger.info(
            String.format(
                "Sending request %s - %s on %s%n%s",
                request.url, request.body, chain.connection(), request.headers
            )
        )
        val response: Response = chain.proceed(request)
        val t2 = System.nanoTime()
        logger.info(
            String.format(
                "Received response for %s %s in %.1fms%n%s",
                response.request.url, response.body, (t2 - t1) / 1e6, response.headers
            )
        )
        return response
    }
}
