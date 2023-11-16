package com.resulgenc.moviehub.data.network.interceptors

import android.content.Context
import com.resulgenc.moviehub.utils.network.CheckNetworkConnection
import com.resulgenc.moviehub.utils.network.NoConnectionException
import okhttp3.Interceptor
import okhttp3.Response


/**
 * Interceptor class for managing network connection status.
 *
 * @param context The application context to access resources and check network connectivity.
 */
class ConnectionInterceptor(context: Context?) : Interceptor {

    // The application context to access resources and check network connectivity.
    private val applicationContext = context?.applicationContext

    // Helper class for checking network connection status.
    private val checkNetworkConnection = CheckNetworkConnection()


    /**
     * Intercepts the request and checks if the device is online.
     *
     * @param chain The interceptor chain.
     * @return The response if the device is online; otherwise, throws a [NoConnectionException].
     */
    override fun intercept(chain: Interceptor.Chain): Response =
        when (checkNetworkConnection.isOnline(applicationContext)) {
            true -> {
                // If online, proceed with the request.
                val builder = chain.request().newBuilder()
                chain.proceed(builder.build())
            }

            false -> throw NoConnectionException()
        }
}