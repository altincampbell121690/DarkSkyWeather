package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("alerts")
    var alerts: List<Alert>,
    @SerializedName("currently")
    var currently: Currently,
    @SerializedName("daily")
    var daily: Daily,
    @SerializedName("flags")
    var flags: Flags,
    @SerializedName("hourly")
    var hourly: Hourly,
    @SerializedName("latitude")
    var latitude: Double,
    @SerializedName("longitude")
    var longitude: Double,
    @SerializedName("minutely")
    var minutely: Minutely,
    @SerializedName("offset")
    var offset: Int,
    @SerializedName("timezone")
    var timezone: String
)