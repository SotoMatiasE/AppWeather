package com.example.appweather.common.entities

data class Forecast( //forecast = Pronostico
    val dt: Long,
    val humidity: Int,
    val temp: Double,
    val weather: List<Weather>,
    val pop: Double
) : WeatherBase(dt, humidity, temp, weather)
