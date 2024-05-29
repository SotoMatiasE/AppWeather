package com.example.appweather.common.entities

data class WeatherForecastEntity( val timezone: String,
                                  val current: Current,
                                  val hourly: List<Forecast>?)
