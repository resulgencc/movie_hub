package com.resulgenc.moviehub.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor class for adding a query parameter, such as an API key, to the outgoing HTTP requests.
 *
 * @param key The key of the query parameter.
 * @param value The value of the query parameter.
 */
class QueryParameterInterceptor(
    private val key: String,
    private val value: Any?,
) : Interceptor {

    /**
     * Intercepts the request and adds a query parameter to it, such as an API key.
     *
     * @param chain The interceptor chain.
     * @return The response after adding the query parameter to the request.
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the original request.
        val original = chain.request()

        // Get the original URL.
        val httpUrl = original.url

        // Create a new URL with the added query parameter.
        val newHttpUrl = httpUrl
            .newBuilder()
            .addQueryParameter(key, value?.toString())
            .build()

        // Create a new request with the modified URL.
        val builder = original
            .newBuilder()
            .url(newHttpUrl)

        // Build the modified request.
        val request = builder.build()

        // Proceed with the modified request and return the response.
        return chain.proceed(request)
    }
}
