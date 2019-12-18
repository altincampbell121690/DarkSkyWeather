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
import com.example.retropractice.Model.Forcast
import com.example.retropractice.R
import com.example.retropractice.Services.RetrofitClient
import com.example.retropractice.Services.WeatherService
import com.example.retropractice.Utilities.*
import com.networkCallback
import retrofit2.converter.gson.GsonConverterFactory

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import retrofit2.*


class MainActivity : AppCompatActivity() {

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    public val RequestPermissionsCode: Int = 1
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

        }

        //   textApiResult
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val weatherService: WeatherService =
//            RetrofitClient.getClient(BASE_URL)!!.create(WeatherService::class.java)
//        val call: Call<Forcast> = weatherService.getWeather(42.3601, -71.0589)


        //no name object
      /*  call.enqueue(object : Callback<Forcast> {
            override fun onFailure(call: Call<Forcast>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
               Log.d("ALTIN",t.message)
            }

            override fun onResponse(call: Call<Forcast>, response: Response<Forcast>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                if (!response.isSuccessful) {
                    Log.d("ALTIN","code: ${response.code()}")
                    isApiConnected = false;
                    return
                }
                isApiConnected = true
                dailyData = response.body()!!.daily.data
//              val dailyData: List<Data> = response.body()!!.daily.data


*//*                for(data in dailyData ){
                    var content:String = "";
                    content += "daily:${data}/n/n"

                    textApiResult.append(content)

                }*//*

            }

        })*/

    }


    private fun checkConnectivity(): Unit {
        val connectionMngr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        val activeNetwork = connectionMngr.activeNetworkInfo
//        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectionMngr.run {
            registerNetworkCallback(networkRequest, networkCallback)
        }
        if (isNetworkConnected) Toast.makeText(this, "connection available", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "NO connection available", Toast.LENGTH_SHORT).show()
    }


    fun onEnterClicked(view: View) {
        val displayWeather = Intent(this, displayWeather::class.java)
        startActivity(displayWeather)
    }


    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf<String>(ACCESS_FINE_LOCATION),
            RequestPermissionsCode
        )
        isPermitted = (ActivityCompat.checkSelfPermission(
            this,
            ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }


}
