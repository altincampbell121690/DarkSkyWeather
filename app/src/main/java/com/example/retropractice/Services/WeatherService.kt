package com.example.retropractice.Services

import com.example.retropractice.Model.Forcast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {
    //@GET("42.3601,-71.0589")
    @GET("{latitude},{longitude}")
    fun getWeather(
        @Path("latitude") latitude: Double?,
        @Path("longitude") longitude: Double?
    ): Call<Forcast>
}
//
//fun getWeather(
//    @Path("latitude") latitude: Double?,
//    @Path("longitude") longitude: Double?
//): Call<Forecast?>?