package com.example.retropractice.Services

import com.example.retropractice.Model.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    //@GET("42.3601,-71.0589")
    @GET("{latitude},{longitude}")
    fun getWeather(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?


    ): Call<Forecast>
}
//
//fun getWeather(
//    @Path("latitude") latitude: Double?,
//    @Path("longitude") longitude: Double?
//): Call<Forecast?>?