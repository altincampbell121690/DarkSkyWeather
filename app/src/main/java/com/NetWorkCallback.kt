package com

import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import android.widget.Toast
import com.example.retropractice.Utilities.isConnected

var networkCallback = object : ConnectivityManager.NetworkCallback() {
    override fun onLost(network: Network?) {
        Log.d("ALTIN","networkcallback called from onLost")
        isConnected = false
        //record wi-fi disconnect event
    }
    override fun onUnavailable() {
        Log.d("ALTIN","networkcallback called from onUnvailable")
        isConnected= false
    }
    override fun onLosing(network: Network?, maxMsToLive: Int) {
        Log.d("ALTIN","networkcallback called from onLosing")
    }
    override fun onAvailable(network: Network?) {
        Log.d("ALTIN","NetworkCallback network called from onAvailable ")
        isConnected = true
        //record wi-fi connect event
    }
}