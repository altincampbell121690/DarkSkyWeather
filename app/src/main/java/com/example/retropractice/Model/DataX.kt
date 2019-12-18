package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class DataX(
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
    @SerializedName("ozone")
    var ozone: Double,
    @SerializedName("precipAccumulation")
    var precipAccumulation: Double,
    @SerializedName("precipIntensity")
    var precipIntensity: Double,
    @SerializedName("precipProbability")
    var precipProbability: Double,
    @SerializedName("precipType")
    var precipType: String,
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