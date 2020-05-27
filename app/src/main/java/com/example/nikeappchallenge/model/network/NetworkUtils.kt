package com.example.nikeappchallenge.model.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService
import com.example.nikeappchallenge.App

object NetworkUtils {

    fun isOnline(): Boolean {
        val connectivityManager =
            App.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork).let{
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                || (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                || (it.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)))  {
                return true
            }
        }
        return false
    }
}