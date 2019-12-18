package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Flags(
    @SerializedName("nearest-station")
    var nearestStation: Double,
    @SerializedName("sources")
    var sources: List<String>,
    @SerializedName("units")
    var units: String
)