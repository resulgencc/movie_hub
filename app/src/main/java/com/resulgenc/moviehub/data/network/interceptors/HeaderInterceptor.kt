package com.resulgenc.moviehub.data.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * Interceptor class for adding custom headers to the outgoing HTTP requests.
 *
 * @param headers The headers to be added to the requests, represented as a HashMap of key-value pairs.
 */
class HeaderInterceptor(private val headers: HashMap<String, Any>) : Interceptor {


    /**
     * Intercepts the request and adds custom headers to it.
     *
     * @param chain The interceptor chain.
     * @return The response after adding the custom headers to the request.
     * @throws IOException If an I/O error occurs during the interception process.
     */
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        // Create a new builder based on the original request.
        val builder = chain.request().newBuilder()

        // Iterate through the provided headers and add them to the request.
        headers.forEach { (key, value) ->
            builder.addHeader(key, value.toString())
        }

        // Build the modified request.
        val request = builder.build()

        // Proceed with the modified request and return the response.
        return chain.proceed(request)
    }
}