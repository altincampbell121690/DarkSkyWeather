package com.example.retropractice.Utilities

import com.example.retropractice.Model.Data
import com.example.retropractice.Model.Forecast
import java.text.SimpleDateFormat
import java.util.*

//const val BASE_URL = "https://api.darksky.net/forecast/"
//const val API_KEY = "b7d4f712c2ef1755ea5f3f61118f9140"

const val BASE_URL = "https://api.darksky.net/forecast/b7d4f712c2ef1755ea5f3f61118f9140/"
//const val  URL_REGISTER ="${BASE_URL}account/register"

var isNetworkConnected: Boolean = false
var dailyData: List<Data> = listOf<Data>()
var isPermitted : Boolean = false
var isApiConnected:Boolean =false
var forecast:Forecast? = null
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

fun toMonth(month: String): Int{
    return when (month){
        "01"-> Calendar.JANUARY
        "02"-> Calendar.FEBRUARY
        "03"-> Calendar.MARCH
        "04"-> Calendar.APRIL
        "05"-> Calendar.MAY
        "06"-> Calendar.JUNE
        "07"-> Calendar.JULY
        "08"-> Calendar.AUGUST
        "09"-> Calendar.SEPTEMBER
        "10"-> Calendar.OCTOBER
        "11"-> Calendar.NOVEMBER
        "12"-> Calendar.DECEMBER
        else -> 11
    }
}
fun regexToString(str:String, regex1:String):String{
    val regX1 = regex1.toRegex()
    var capture: String = ""
    var matchResult = regX1.find(str)
    if (matchResult != null) {
        println(matchResult.groupValues)
        capture += matchResult.groupValues[1]
    }
    return capture
}



fun getCurrTime(unix_seconds: Long): String {
    //Unix seconds
    // val unix_seconds: Long = forcast!!.currently.time.toLong()
    //convert seconds to milliseconds
    val date = Date(unix_seconds * 1000L)
    // format of the date
    val jdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa")

    val calendar: Calendar = GregorianCalendar()
    val timeZone = calendar.timeZone
    val gmtZone = getGMT(timeZone.displayName)

    jdf.timeZone = TimeZone.getTimeZone(gmtZone)

//                            jdf.timeZone = TimeZone.getTimeZone("GMT-8")
    val java_date: String = jdf.format(date)

    val regex1 = """([1-9]?\d:\d\d)"""
    val regex2 = """( [a-zA-Z][a-zA-Z])"""
    val time = regexConcat(java_date,regex1,regex2)
    println("----Altin--------\n$java_date\n----Altin--------\n")
//        println("\n\n${regex.find(java_date)!!.value}\n\n")


    return time

}

fun getCurrDate(unix_seconds: Long): String {
    //Unix seconds
    // val unix_seconds: Long = forcast!!.currently.time.toLong()
    //convert seconds to milliseconds
    val date = Date(unix_seconds * 1000L)
    // format of the date
    val jdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss aa")

    val calendar: Calendar = GregorianCalendar()
    val timeZone = calendar.timeZone
    val gmtZone = getGMT(timeZone.displayName)

    jdf.timeZone = TimeZone.getTimeZone(gmtZone)

//                            jdf.timeZone = TimeZone.getTimeZone("GMT-8")
    val java_date: String = jdf.format(date)
    println("----Altin--------\n$java_date\n----Altin--------\n")
//        println("\n\n${regex.find(java_date)!!.value}\n\n")


    return java_date

}

