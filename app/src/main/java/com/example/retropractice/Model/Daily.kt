package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Daily(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("summary")
    var summary: String
)