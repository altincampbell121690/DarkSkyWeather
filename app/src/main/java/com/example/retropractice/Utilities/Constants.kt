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
var long: Double? = null
var lat: Double? = null

fun getGMT(timeDisplay : String):String{
    return when(timeDisplay){
        "British Summer Time","Irish Summer Time",
        "Western European Daylight Time",
        "WEST Western European Summer Time" -> "GMT+1"

        "West Africa Time" -> "GMT-1"
        "Azores Time" -> "GMT-2"
        "Argentina Time" -> "GMT-3"
        "Atlantic Standard Time" -> "GMT-4"
        "Eastern Standard Time"-> "GMT-5"
        "Central Standard Time" -> "GMT-6"
        "Mountain Standard Time" -> "GMT-7"
        "Pacific Standard Time" -> "GMT-8"
        "Alaska Standard Time",
        "Yukon Standard Time" -> "GMT-9"
        "Hawaii Standard Time",
        "Hawaii-Aleutian Standard Time",
        "Alaska-Hawaii Standard Time",
        "Central Alaska Time" -> "GMT-10"
        "Nome Time" -> "GMT-11"
        "International Date Line West"-> "GMT-12"
        else -> "GMT"
    }
}

fun regexConcat(str: String, regex1: String, regex2: String): String {
    val regX1 = regex1.toRegex()
    val regX2 = regex2.toRegex()
    var capture: String = ""
    var matchResult = regX1.find(str)
    if (matchResult != null) {
        println(matchResult.groupValues)
        capture += matchResult.groupValues[1]
    }
    matchResult = regX2.find(str)
    if (matchResult != null) {
        println(matchResult.groupValues)
        capture += matchResult.groupValues[1]
    }
    return capture
}