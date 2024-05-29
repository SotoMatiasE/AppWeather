package com.example.appweather.common

data class Current(
    val dt: Long,
    val humidity: Int,
    val temp: Double,
    val weather: List<Weather>,
    val sunrise: Long) : WeatherBase(dt, humidity, temp, weather)
