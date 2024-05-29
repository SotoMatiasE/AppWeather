package com.example.appweather.common

data class WeatherForecastEntity(val timZone: String,
                                 val current: Current,
                                 val hourly: List<Forecast>)
