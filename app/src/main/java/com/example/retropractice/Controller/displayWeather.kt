package com.example.retropractice.Controller

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.retropractice.Model.Forcast
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
                    val call: Call<Forcast> =
                        weatherService.getWeather(location.latitude, location.longitude)

                    call.enqueue(object : Callback<Forcast> {
                        override fun onFailure(call: Call<Forcast>, t: Throwable) {

                            Log.d("ALTIN", t.message)
                        }

                        override fun onResponse(call: Call<Forcast>, response: Response<Forcast>) {

                            if (!response.isSuccessful) {
                                Log.d("ALTIN", "code: ${response.code()}")
                                isApiConnected = false;
                                return
                            }
                            isApiConnected = true
                            forcast = response.body()
                            dailyData = forcast!!.daily.data

                            locationTxt.text = forcast!!.timezone
                            currentTempTxt.text = "${forcast!!.currently.apparentTemperature} F"
                            // currentTimeTxt.text = java.time.format.DateTimeFormatter.ISO_INSTANT.format(java.time.Instant.ofEpochSecond(forcast!!.currently.time.toLong()))
                            currentTimeTxt.text = getCurrTime(forcast!!.currently.time.toLong())


/*                            //Unix seconds
                            val unix_seconds: Long = forcast!!.currently.time.toLong()
                            //convert seconds to milliseconds
                            val date = Date(unix_seconds * 1000L)
                            // format of the date
                            val jdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa")

                            val calendar: Calendar = GregorianCalendar()
                            val timeZone = calendar.timeZone
                            val gmtZone = getGMT(timeZone.displayName)

                            jdf.timeZone = TimeZone.getTimeZone(gmtZone)

//                            jdf.timeZone = TimeZone.getTimeZone("GMT-8")
                            val java_date: String = jdf.format(date)
                            println("----Altin--------\n ${timeZone.displayName}\n" + java_date + "\n----Altin--------\n")*/
/*
/////////////////////////////////////////////////////////////////////////////////
                            locationTxt.text = forcast!!.timezone
                            val zuluTime= java.time.format.DateTimeFormatter.ISO_INSTANT
                                .format(java.time.Instant.ofEpochSecond(forcast!!.currently.time.toLong()))
                            val regex = """(\d\d:\d\d)""".toRegex()
                            val matchResult = regex.find(zuluTime)

                            currentTempTxt.text = "${forcast!!.currently.apparentTemperature} F"
                            //currentTimeTxt.text


                            *//*   //Unix seconds
                               //Unix seconds
                               val unix_seconds: Long = forcast!!.currently.time.toLong()
                               //convert seconds to milliseconds
                               //convert seconds to milliseconds
                               val date = Date(unix_seconds * 1000L)
                               // format of the date
                               // format of the date
                               val jdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss z")
                               jdf.timeZone = TimeZone.getTimeZone("GMT-4")
                               val java_date: String = jdf.format(date)
                               println("\n" + java_date + "\n")*/




                            Log.d("ALTIN JSON", "$forcast")
                            Log.d("ALTIN JSON", forcast!!.timezone)
                            Log.d("ALTIN JSON", dailyData.toString())


                        }

                    })
                } else {
                    Log.d("ALTIN", "LOCATION NULL")
                }
            }

        Log.d("ALTIN", "$lat")
        /* val weatherService: WeatherService =
             RetrofitClient.getClient(BASE_URL)!!.create(WeatherService::class.java)
         Log.d("ALTIN!!!!!!!","$lat, $long")
         Log.d("ALTIN*****","${locationDataManager.readLocation("LAT")} ${locationDataManager.readLocation("LONG")}")
         val call: Call<Forcast> = weatherService.getWeather(locationDataManager.readLocation("LAT"), locationDataManager.readLocation("LONG"))

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
                 Log.d("ALTIN JSON", forcast!!.timezone)
                 Log.d("ALTIN JSON", dailyData.toString())

               //  dailyData = response.body()!!.daily.data
 //              val dailyData: List<Data> = response.body()!!.daily.data


 *//*                for(data in dailyData ){
                    var content:String = "";
                    content += "daily:${data}/n/n"

                    textApiResult.append(content)

                }*//*

            }

        })*/
    }

    override fun onStart() {
        super.onStart()
        if (locationDataManager.readLocation("LAT") != (-1).toDouble()) {
            Log.d("ALTIN TEST!!!!!!!!!", locationDataManager.readLocation("LAT").toString())

            lat = locationDataManager.readLocation("LAT")
            long = locationDataManager.readLocation("LONG")
        }
    }

    fun getCurrTime(unix_seconds: Long): String {
        //Unix seconds
        // val unix_seconds: Long = forcast!!.currently.time.toLong()
        //convert seconds to milliseconds
        val date = Date(unix_seconds * 1000L)
        // format of the date
        val jdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa")

        val calendar: Calendar = GregorianCalendar()
        val timeZone = calendar.timeZone
        val gmtZone = getGMT(timeZone.displayName)

        jdf.timeZone = TimeZone.getTimeZone(gmtZone)

//                            jdf.timeZone = TimeZone.getTimeZone("GMT-8")
        val java_date: String = jdf.format(date)

        val regex1 = """([1-9]?\d:\d\d)"""
        val regex2 = """( [a-zA-Z][a-zA-Z])"""
        val time = regexConcat(java_date,regex1,regex2)
//        println("----Altin--------\n$java_date\n----Altin--------\n")
//        println("\n\n${regex.find(java_date)!!.value}\n\n")

        return time

    }


}
