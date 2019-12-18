package com.example.retropractice.Controller

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.retropractice.Model.Data
import com.example.retropractice.Model.Forcast
import com.example.retropractice.R
import com.example.retropractice.Services.RetrofitClient
import com.example.retropractice.Services.WeatherService
import com.example.retropractice.Utilities.BASE_URL
import com.example.retropractice.Utilities.isConnected
import com.networkCallback
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkConnectivity()

     //   textApiResult
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherService: WeatherService = retrofit.create(
            WeatherService::class.java
        )
        val call: Call<Forcast> = weatherService.getWeather(42.3601,-71.0589)



 //no name object
        call.enqueue(object: Callback<Forcast>{
            override fun onFailure(call: Call<Forcast>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                textApiResult.text = t.message
            }

            override fun onResponse(call: Call<Forcast>, response: Response<Forcast>) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                if (!response.isSuccessful){
                    textApiResult.text = "code: ${response.code()}"
                    return
                }
                val dailyData: List<Data> = response.body()!!.daily.data

                for(data in dailyData ){
                    var content:String = "";
                    content += "daily:${data}/n/n"

                    textApiResult.append(content)

                }
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

}
