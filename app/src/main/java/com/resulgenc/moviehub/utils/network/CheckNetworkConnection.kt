package com.resulgenc.moviehub.utils.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Utility class for checking network connection status.
 */
class CheckNetworkConnection {

    /**
     * Checks if the device is currently online and connected to a network.
     *
     * @param context The application context.
     * @return `true` if the device is online; otherwise, `false`.
     */
    fun isOnline(context: Context?): Boolean {
        // Get the ConnectivityManager service from the context.
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

        // Get the network capabilities of the active network.
        val capabilities = connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)

        // Check if the network capabilities are not null.
        if (capabilities != null) {
            // Check the type of transport available and return true if online.
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }

        // Return false if the device is not online or connected to a network.
        return false
    }
}
