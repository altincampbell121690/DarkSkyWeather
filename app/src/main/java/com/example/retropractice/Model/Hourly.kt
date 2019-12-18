package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Hourly(
    @SerializedName("data")
    var `data`: List<DataX>,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("summary")
    var summary: String
)