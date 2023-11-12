package com.resulgenc.moviehub.utils.network

import java.io.IOException

/**
 * Custom exception class representing a lack of internet connection.
 *
 * This exception is thrown when an operation requires internet connectivity, but none is available.
 */
class NoConnectionException : IOException() {
    override val message = "No Internet Connection"
}