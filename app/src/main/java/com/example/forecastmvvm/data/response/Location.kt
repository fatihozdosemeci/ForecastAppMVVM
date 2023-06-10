package com.example.forecastmvvm.data.response


import com.google.gson.annotations.SerializedName

data class Location(
    val country: String,
    val lat: String,
    val localtime: String,
    @SerializedName("localtime_epoch")
    val localtimeEpoch: Int,
    val lon: String,
    val name: String,
    val region: String,
    @SerializedName("timezone_id")
    val timezoneİd: String,
    @SerializedName("utc_offset")
    val utcOffset: String
)