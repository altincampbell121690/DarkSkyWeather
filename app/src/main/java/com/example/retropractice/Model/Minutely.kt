package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Minutely(
    @SerializedName("data")
    var `data`: List<DataXX>,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("summary")
    var summary: String
)