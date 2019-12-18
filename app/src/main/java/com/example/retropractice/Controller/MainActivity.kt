package com.example.retropractice.Controller

import android.Manifest
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.retropractice.Model.Data
import com.example.retropractice.Model.Forcast
import com.example.retropractice.R
import com.example.retropractice.Services.RetrofitClient
import com.example.retropractice.Services.WeatherService
import com.example.retropractice.Utilities.BASE_URL
import com.example.retropractice.Utilities.dailyData
import com.example.retropractice.Utilities.isConnected
import com.google.android.gms.common.ConnectionResult
import com.networkCallback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.OnSuccessListener


class MainActivity : AppCompatActivity(), ConnectionCallbacks, OnConnectionFailedListener {

    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    public val RequestPermissionsCode: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectivity()

        longitudeTxt.text = "test Longitude"
        latitudeTxt.text = "test Latitude"

        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        //   textApiResult
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()



        val weatherService: WeatherService = retrofit.create(
            WeatherService::class.java
        )
        val call: Call<Forcast> = weatherService.getWeather(42.3601, -71.0589)


        //no name object
        call.enqueue(object : Callback<Forcast> {
            override fun onFailure(call: Call<Forcast>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                textApiResult.text = t.message
            }

            override fun onResponse(call: Call<Forcast>, response: Response<Forcast>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                if (!response.isSuccessful) {
                    textApiResult.text = "code: ${response.code()}"
                    return
                }
//                val dailyData: List<Data> = response.body()!!.daily.data
                dailyData = response.body()!!.daily.data

//                for(data in dailyData ){
//                    var content:String = "";
//                    content += "daily:${data}/n/n"
//
//                    textApiResult.append(content)
//
//                }

            }

        })

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
        if (isConnected) Toast.makeText(this, "connection available", Toast.LENGTH_SHORT).show()
        else Toast.makeText(this, "NO connection available", Toast.LENGTH_SHORT).show()
    }

    override fun onConnected(p0: Bundle?) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
       Log.d("ALTIN",ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION).toString())
        if (ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
           val granted = requestPermissions()
        }else{
            fusedLocationProviderClient.lastLocation.addOnSuccessListener(
                this,
                object : OnSuccessListener<Location> {
                    override
                    fun onSuccess(location: Location?) {
                        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                        if (location != null) {
                            longitudeTxt.text = location.latitude.toString()
                            latitudeTxt.text = location.longitude.toString()
                        }else{
                            Log.d("ALTIN", "NOT GONNA WORK")
                        }
                    }
                })
            val weatherInten = Intent(this,displayWeather::class.java)
            startActivity(weatherInten)
        }



    }

    private fun requestPermissions(): Boolean {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf<String>(ACCESS_FINE_LOCATION) ,
            RequestPermissionsCode
        )
        return (ActivityCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
    }

    override fun onConnectionSuspended(p0: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ALTIN", "CONNECTION Suspended")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ALTIN", "CONNECTION FAILED ${p0.errorCode}")
    }

    override fun onStart(){
        super.onStart()
        googleApiClient.connect()
        Log.d("ALTIn", "COnnected")
    }

    override fun onStop(){
        if(googleApiClient.isConnected) googleApiClient.disconnect()
        super.onStop()
        Log.d("ALTIn", "DISCOnnected")
    }


}
