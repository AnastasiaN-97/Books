package com.example.books

import android.annotation.TargetApi
import android.content.Context
import android.net.*
import android.os.Build
import androidx.lifecycle.LiveData
import java.net.NetworkInterface

class NetworkConnections(private val context: Context): LiveData<Boolean>() {
    private val connectivityManager: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback


    //вызывается только на данном уровне апи или выше, в данном слуаче 5+
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest() {
        val requestBuilder = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        connectivityManager.registerNetworkCallback(requestBuilder.build(), connectivityManagerCallBack())
    }

    private fun connectivityManagerCallBack(): ConnectivityManager.NetworkCallback {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            networkCallback = object : ConnectivityManager.NetworkCallback() {

                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }
            }

            return networkCallback
        } else {
            throw IllegalAccessError("Error")
        }
    }

    private fun updateConnection() {
        val activeNetwork:  NetworkInfo?
    }
}