package com.example.appweather.common.entities

data class WeatherForecastEntity(val timZone: String,
                                 val current: Current,
                                 val hourly: List<Forecast>)
