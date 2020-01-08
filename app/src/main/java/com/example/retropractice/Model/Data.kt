package com.example.retropractice.Model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("apparentTemperatureHigh")
    var apparentTemperatureHigh: Double,
    @SerializedName("apparentTemperatureHighTime")
    var apparentTemperatureHighTime: Int,
    @SerializedName("apparentTemperatureLow")
    var apparentTemperatureLow: Double,
    @SerializedName("apparentTemperatureLowTime")
    var apparentTemperatureLowTime: Int,
    @SerializedName("apparentTemperatureMax")
    var apparentTemperatureMax: Double,
    @SerializedName("apparentTemperatureMaxTime")
    var apparentTemperatureMaxTime: Int,
    @SerializedName("apparentTemperatureMin")
    var apparentTemperatureMin: Double,
    @SerializedName("apparentTemperatureMinTime")
    var apparentTemperatureMinTime: Int,
    @SerializedName("cloudCover")
    var cloudCover: Double,
    @SerializedName("dewPoint")
    var dewPoint: Double,
    @SerializedName("humidity")
    var humidity: Double,
    @SerializedName("icon")
    var icon: String,
    @SerializedName("moonPhase")
    var moonPhase: Double,
    @SerializedName("ozone")
    var ozone: Double,
    @SerializedName("precipAccumulation")
    var precipAccumulation: Double,
    @SerializedName("precipIntensity")
    var precipIntensity: Double,
    @SerializedName("precipIntensityMax")
    var precipIntensityMax: Double,
    @SerializedName("precipIntensityMaxTime")
    var precipIntensityMaxTime: Double,
    @SerializedName("precipProbability")
    var precipProbability: Double,
    @SerializedName("precipType")
    var precipType: String,
    @SerializedName("pressure")
    var pressure: Double,
    @SerializedName("summary")
    var summary: String,
    @SerializedName("sunriseTime")
    var sunriseTime: Int,
    @SerializedName("sunsetTime")
    var sunsetTime: Int,
    @SerializedName("temperatureHigh")
    var temperatureHigh: Double,
    @SerializedName("temperatureHighTime")
    var temperatureHighTime: Int,
    @SerializedName("temperatureLow")
    var temperatureLow: Double,
    @SerializedName("temperatureLowTime")
    var temperatureLowTime: Int,
    @SerializedName("temperatureMax")
    var temperatureMax: Double,
    @SerializedName("temperatureMaxTime")
    var temperatureMaxTime: Int,
    @SerializedName("temperatureMin")
    var temperatureMin: Double,
    @SerializedName("temperatureMinTime")
    var temperatureMinTime: Int,
    @SerializedName("time")
    var time: Int,
    @SerializedName("uvIndex")
    var uvIndex: Int,
    @SerializedName("uvIndexTime")
    var uvIndexTime: Int,
    @SerializedName("visibility")
    var visibility: Double,
    @SerializedName("windBearing")
    var windBearing: Int,
    @SerializedName("windGust")
    var windGust: Double,
    @SerializedName("windGustTime")
    var windGustTime: Int,
    @SerializedName("windSpeed")
    var windSpeed: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(apparentTemperatureHigh)
        parcel.writeInt(apparentTemperatureHighTime)
        parcel.writeDouble(apparentTemperatureLow)
        parcel.writeInt(apparentTemperatureLowTime)
        parcel.writeDouble(apparentTemperatureMax)
        parcel.writeInt(apparentTemperatureMaxTime)
        parcel.writeDouble(apparentTemperatureMin)
        parcel.writeInt(apparentTemperatureMinTime)
        parcel.writeDouble(cloudCover)
        parcel.writeDouble(dewPoint)
        parcel.writeDouble(humidity)
        parcel.writeString(icon)
        parcel.writeDouble(moonPhase)
        parcel.writeDouble(ozone)
        parcel.writeDouble(precipAccumulation)
        parcel.writeDouble(precipIntensity)
        parcel.writeDouble(precipIntensityMax)
        parcel.writeDouble(precipIntensityMaxTime)
        parcel.writeDouble(precipProbability)
        parcel.writeString(precipType)
        parcel.writeDouble(pressure)
        parcel.writeString(summary)
        parcel.writeInt(sunriseTime)
        parcel.writeInt(sunsetTime)
        parcel.writeDouble(temperatureHigh)
        parcel.writeInt(temperatureHighTime)
        parcel.writeDouble(temperatureLow)
        parcel.writeInt(temperatureLowTime)
        parcel.writeDouble(temperatureMax)
        parcel.writeInt(temperatureMaxTime)
        parcel.writeDouble(temperatureMin)
        parcel.writeInt(temperatureMinTime)
        parcel.writeInt(time)
        parcel.writeInt(uvIndex)
        parcel.writeInt(uvIndexTime)
        parcel.writeDouble(visibility)
        parcel.writeInt(windBearing)
        parcel.writeDouble(windGust)
        parcel.writeInt(windGustTime)
        parcel.writeDouble(windSpeed)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Data> {
        override fun createFromParcel(parcel: Parcel): Data {
            return Data(parcel)
        }

        override fun newArray(size: Int): Array<Data?> {
            return arrayOfNulls(size)
        }
    }
}