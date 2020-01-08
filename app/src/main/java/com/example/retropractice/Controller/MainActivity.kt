package com.example.retropractice.Controller

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.retropractice.R
import com.example.retropractice.Utilities.LocationDataManager
import com.example.retropractice.Utilities.isPermitted
import com.example.retropractice.Utilities.lat
import com.example.retropractice.Utilities.long
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.networkCallback


class MainActivity : AppCompatActivity() {

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    public val RequestPermissionsCode: Int = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    //var locationDataManager = LocationDataManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectivity()
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissions()
        } else {
            Log.d("ALTIN", "PERMISSION Given previously")
            val displayWeather = Intent(this, displayWeather::class.java)
            startActivity(displayWeather)

        }


    }


    private fun checkConnectivity(): Unit {
        val connectionMngr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var status = null
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectionMngr.run {
            registerNetworkCallback(networkRequest, networkCallback)
        }

    }

    fun onEnterClicked(view: View) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this,"NOT PERMITTED", Toast.LENGTH_SHORT).show()
        }else{
            val displayWeather = Intent(this, displayWeather::class.java)
            startActivity(displayWeather)
        }

    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf<String>(ACCESS_FINE_LOCATION),
            RequestPermissionsCode
        )
    }


}
/* override fun onStart() {
     super.onStart()
     val mLocationRequest = LocationRequest.create()
     mLocationRequest.interval = 60000
     mLocationRequest.fastestInterval = 5000
     mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
     val mLocationCallback: LocationCallback = object : LocationCallback() {
         override fun onLocationResult(locationResult: LocationResult) {
             if (locationResult == null) {
                 Log.d("ALTIN***", "LOCATION IS NULLL IN ONSTART")
                 return
             }
             for (location in locationResult.locations) {
                 if (location != null) {
                     Log.d("ALTIN", "ONSTART  LOC")
                     Log.d("ALTIN!!!!!!!","${location.latitude}, ${long}")
                     locationDataManager.saveLocation(location.latitude, location.longitude)
                 }
             }
         }
     }
     LocationServices.getFusedLocationProviderClient(this)
         .requestLocationUpdates(mLocationRequest, mLocationCallback, null)
 }*/