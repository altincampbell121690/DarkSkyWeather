package com.example.retropractice.Utilities

import android.content.Context
import androidx.preference.PreferenceManager

class LocationDataManager(val context: Context) {
    fun saveLocation(latitude: Double,longitude: Double){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
       // sharedPreferences.putFloat("LAT", latitude.toFloat())
        //sharedPreferences.putFloat("LONG", longitude.toFloat())
        sharedPreferences.putString("LAT", latitude.toString())
        sharedPreferences.putString("LONG", longitude.toString())
        sharedPreferences.apply()
    }

    fun readLocation(location : String): Double{
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        return if (sharedPreferences.getString(location,null) != null){
            sharedPreferences.getString(location,"0")!!.toDouble()
        } else {
            return (-1).toDouble()
            //throw NullPointerException("Expression 'sharedPreferences.getString(location,null)' must not be null")
        }
    }

}