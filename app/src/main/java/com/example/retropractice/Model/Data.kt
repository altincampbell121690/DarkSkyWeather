package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("apparentTemperatureHigh")
    var apparentTemperatureHigh: Double,
    @SerializedName("apparentTemperatureHighTime")
    var apparentTemperatureHighTime: Int,
    @SerializedName("apparentTemperatureLow")
    var apparentTemperatureLow: Double,
    @SerializedName("apparentTemperatureLowTime")
    var apparentTemperatureLowTime: Int,
    @SerializedName("apparentTemperatureMax")
    var apparentTemperatureMax: Double,
    @SerializedName("apparentTemperatureMaxTime")
    var apparentTemperatureMaxTime: Int,
    @SerializedName("apparentTemperatureMin")
    var apparentTemperatureMin: Double,
    @SerializedName("apparentTemperatureMinTime")
    var apparentTemperatureMinTime: Int,
    @SerializedName("cloudCover")
    var cloudCover: Double,
    @SerializedName("dewPoint")
    var dewPoint: Double,
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("moonPhase")
    var moonPhase: Double,
    @SerializedName("ozone")
    var ozone: Double,
    @SerializedName("precipAccumulation")
    var precipAccumulation: Double,
    @SerializedName("precipIntensity")
    var precipIntensity: Double,
    @SerializedName("precipIntensityMax")
    var precipIntensityMax: Double,
    @SerializedName("precipIntensityMaxTime")
    var precipIntensityMaxTime: Double,
    @SerializedName("precipProbability")
    var precipProbability: Double,
    @SerializedName("precipType")
    var precipType: String,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("summary")
    var summary: String,
    @SerializedName("sunriseTime")
    var sunriseTime: Int,
    @SerializedName("sunsetTime")
    var sunsetTime: Int,
    @SerializedName("temperatureHigh")
    var temperatureHigh: Double,
    @SerializedName("temperatureHighTime")
    var temperatureHighTime: Int,
    @SerializedName("temperatureLow")
    var temperatureLow: Double,
    @SerializedName("temperatureLowTime")
    var temperatureLowTime: Int,
    @SerializedName("temperatureMax")
    var temperatureMax: Double,
    @SerializedName("temperatureMaxTime")
    var temperatureMaxTime: Int,
    @SerializedName("temperatureMin")
    var temperatureMin: Double,
    @SerializedName("temperatureMinTime")
    var temperatureMinTime: Int,
    @SerializedName("time")
    var time: Int,
    @SerializedName("uvIndex")
    var uvIndex: Int,
    @SerializedName("uvIndexTime")
    var uvIndexTime: Int,
    @SerializedName("visibility")
    var visibility: Double,
    @SerializedName("windBearing")
    var windBearing: Int,
    @SerializedName("windGust")
    var windGust: Double,
    @SerializedName("windGustTime")
    var windGustTime: Int,
    @SerializedName("windSpeed")
    var windSpeed: Double
)