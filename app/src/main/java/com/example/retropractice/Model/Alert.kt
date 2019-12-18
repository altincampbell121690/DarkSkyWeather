package com.example.retropractice.Model


import com.google.gson.annotations.SerializedName

data class Alert(
    @SerializedName("description")
    var description: String,
    @SerializedName("expires")
    var expires: Int,
    @SerializedName("regions")
    var regions: List<String>,
    @SerializedName("severity")
    var severity: String,
    @SerializedName("time")
    var time: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("uri")
    var uri: String
)