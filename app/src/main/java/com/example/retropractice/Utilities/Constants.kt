package com.example.retropractice.Utilities

import com.example.retropractice.Model.Data
import com.example.retropractice.Model.Forcast

//const val BASE_URL = "https://api.darksky.net/forecast/"
//const val API_KEY = "b7d4f712c2ef1755ea5f3f61118f9140"

const val BASE_URL = "https://api.darksky.net/forecast/b7d4f712c2ef1755ea5f3f61118f9140/"
//const val  URL_REGISTER ="${BASE_URL}account/register"

var isNetworkConnected: Boolean = false
var dailyData: List<Data> = listOf<Data>()
var isPermitted : Boolean = false
var isApiConnected:Boolean =false
var forcast:Forcast? = null