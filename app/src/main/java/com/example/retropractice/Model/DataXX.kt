package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class DataXX(
    @SerializedName("precipIntensity")
    var precipIntensity: Double,
    @SerializedName("precipIntensityError")
    var precipIntensityError: Double,
    @SerializedName("precipProbability")
    var precipProbability: Double,
    @SerializedName("precipType")
    var precipType: String,
    @SerializedName("time")
    var time: Int
)