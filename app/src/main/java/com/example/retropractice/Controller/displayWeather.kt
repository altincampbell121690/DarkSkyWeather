package com.example.retropractice.Controller

import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retropractice.Adapters.dailyItemRecyclerViewAdapter
import com.example.retropractice.Model.Data
import com.example.retropractice.Model.Forecast
import com.example.retropractice.R
import com.example.retropractice.Services.RetrofitClient
import com.example.retropractice.Services.WeatherService
import com.example.retropractice.Utilities.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_display_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class displayWeather : AppCompatActivity() {
    private lateinit var googleApiClient: GoogleApiClient
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    public val RequestPermissionsCode: Int = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    lateinit var myAdapter: dailyItemRecyclerViewAdapter
    var locationDataManager = LocationDataManager(this)
    //val square = { number: Int -> number * number }
    val celToFer = { temp: Double -> temp * (9 / 5).toDouble() + 32 }//(57°C × 9/5) + 32 = 134.6°F


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_weather)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        fusedLocationClient.lastLocation
            .addOnSuccessListener(
                this
            ) { location ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) { // Logic to handle location object
//                    long = location.latitude
//                    lat = location.longitude
                    Log.d("ALTIN-loc", "${location.latitude} ${location.longitude}")
                    locationDataManager.saveLocation(location.latitude, location.longitude)
                    val weatherService: WeatherService =
                        RetrofitClient.getClient(BASE_URL)!!.create(WeatherService::class.java)
                    val call: Call<Forecast> =
                        weatherService.getWeather(location.latitude, location.longitude)

                    call.enqueue(object : Callback<Forecast> {
                        override fun onFailure(call: Call<Forecast>, t: Throwable) {

                            Log.d("ALTIN", t.message)
                        }

                        override fun onResponse(call: Call<Forecast>, response: Response<Forecast>) {

                            if (!response.isSuccessful) {
                                Log.d("ALTIN", "code: ${response.code()}")
                                isApiConnected = false;
                                return
                            }
                            isApiConnected = true
                           val forecast: Forecast? = response.body()
                            displayData(forecast)

                            Log.d("<--ALTIN-->", java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(forecast!!.currently.time.toLong())))
                           /* dailyData = forecast!!.daily.data

                            locationTxt.text = forecast!!.timezone
                            currentTempTxt.text = "${forecast!!.currently.apparentTemperature}° F"
                            // currentTimeTxt.text = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(forcast!!.currently.time.toLong()))
                            currentTimeTxt.text = getCurrTime(forecast!!.currently.time.toLong())
                            summaryTxt.text = forecast.currently.summary
                            percipTxt.text = forecast.currently.precipIntensity.toString()
                            HighTempText.text = "${forecast.daily.data[0].temperatureHigh.toString()}°"
                            LowtempTxt.text = "${forecast.daily.data[0].temperatureLow.toString()}°"
                            windTxt.text = "${forecast.daily.data[0].windSpeed}mph"


                            myAdapter = dailyItemRecyclerViewAdapter(applicationContext, dailyData){data:Data ->
                                Toast.makeText(applicationContext,"clicked", Toast.LENGTH_SHORT).show()
                            }
*/


                            Log.d("ALTIN JSON", "$forecast")
                            Log.d("ALTIN JSON", forecast!!.timezone)
                            Log.d("ALTIN JSON", dailyData.toString())


                        }

                    })
                } else {
                    Log.d("ALTIN", "LOCATION NULL")
                    Toast.makeText(this, "LOCATION NOT FOUND", Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        if (locationDataManager.readLocation("LAT") != (-1).toDouble()) {
            Log.d("ALTIN TEST!!!!!!!!!", locationDataManager.readLocation("LAT").toString())

            lat = locationDataManager.readLocation("LAT")
            long = locationDataManager.readLocation("LONG")
        }
    }

    // move to model


    fun displayData(forecast: Forecast?) {
        Toast.makeText(this, "",Toast.LENGTH_SHORT).show()
        dailyData = forecast!!.daily.data

        locationTxt.text = forecast!!.timezone
        currentTempTxt.text = "${forecast!!.currently.apparentTemperature}° F"
        // currentTimeTxt.text = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(forcast!!.currently.time.toLong()))
        currentTimeTxt.text = getCurrTime(forecast!!.currently.time.toLong())
        summaryTxt.text = forecast.currently.summary
        percipTxt.text = forecast.currently.precipIntensity.toString()
        HighTempText.text = "${forecast.daily.data[0].temperatureHigh.toString()}°"
        LowtempTxt.text = "${forecast.daily.data[0].temperatureLow.toString()}°"
        windTxt.text = "${forecast.daily.data[0].windSpeed}mph"


        myAdapter = dailyItemRecyclerViewAdapter(this, dailyData){data:Data ->
            Toast.makeText(this,"clicked", Toast.LENGTH_SHORT).show()
        }

        dailyWeatherListView.adapter = myAdapter

        val layoutManager = LinearLayoutManager(this)
        dailyWeatherListView.layoutManager = layoutManager


    }

}
