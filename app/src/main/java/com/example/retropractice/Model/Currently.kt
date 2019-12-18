package com.example.retropractice.Model

 // using plug in JSON to KOTLIN
import com.google.gson.annotations.SerializedName

data class Currently(
    @SerializedName("apparentTemperature")
    var apparentTemperature: Double,
    @SerializedName("cloudCover")
    var cloudCover: Double,
    @SerializedName("dewPoint")
    var dewPoint: Double,
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("nearestStormBearing")
    var nearestStormBearing: Int,
    @SerializedName("nearestStormDistance")
    var nearestStormDistance: Int,
    @SerializedName("ozone")
    var ozone: Double,
    @SerializedName("precipIntensity")
    var precipIntensity: Double,
    @SerializedName("precipProbability")
    var precipProbability: Double,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("summary")
    var summary: String,
    @SerializedName("temperature")
    var temperature: Double,
    @SerializedName("time")
    var time: Int,
    @SerializedName("uvIndex")
    var uvIndex: Int,
    @SerializedName("visibility")
    var visibility: Double,
    @SerializedName("windBearing")
    var windBearing: Int,
    @SerializedName("windGust")
    var windGust: Double,
    @SerializedName("windSpeed")
    var windSpeed: Double
)