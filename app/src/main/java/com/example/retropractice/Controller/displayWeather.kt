package com.example.retropractice.Controller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.retropractice.Model.Forcast
import com.example.retropractice.R
import com.example.retropractice.Services.RetrofitClient
import com.example.retropractice.Services.WeatherService
import com.example.retropractice.Utilities.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.thbs.skycons.library.Cloud
import kotlinx.android.synthetic.main.activity_display_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class displayWeather : AppCompatActivity() {
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    public val RequestPermissionsCode: Int = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_weather)


//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//        fusedLocationClient.lastLocation
//            .addOnSuccessListener(
//                this
//            ) { location ->
//                // Got last known location. In some rare situations this can be null.
//                if (location != null) { // Logic to handle location object
//                    long = location.latitude
//                    lat = location.longitude
//                } else {
//                    Log.d("ALTIN", "LOCATION NULL")
//                }
//            }
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
        Log.d("ALTIN","$lat")
        val weatherService: WeatherService =
            RetrofitClient.getClient(BASE_URL)!!.create(WeatherService::class.java)
        Log.d("ALTIN!!!!!!!","$lat, $long")
        val call: Call<Forcast> = weatherService.getWeather(lat, long)

        call.enqueue(object : Callback<Forcast> {
            override fun onFailure(call: Call<Forcast>, t: Throwable) {

                Log.d("ALTIN",t.message)
            }

            override fun onResponse(call: Call<Forcast>, response: Response<Forcast>) {

                if (!response.isSuccessful) {
                    Log.d("ALTIN","code: ${response.code()}")
                    isApiConnected = false;
                    return
                }
                isApiConnected = true
                forcast = response.body()
                dailyData = forcast!!.daily.data

                locationTxt.text = forcast!!.timezone
                Log.d("ALTIN JSON", "$forcast")
                Log.d("ALTIN JSON", dailyData.toString())

              //  dailyData = response.body()!!.daily.data
//              val dailyData: List<Data> = response.body()!!.daily.data


/*                for(data in dailyData ){
                    var content:String = "";
                    content += "daily:${data}/n/n"

                    textApiResult.append(content)

                }*/

            }

        })
    }
}
