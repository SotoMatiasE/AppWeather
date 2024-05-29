package com.example.appweather.mainModule.model

import com.example.appweather.common.entities.WeatherForecastEntity

class MainRepository {
    //clase del repository de la api
    private val remoteDataBase = RemoteDataBase()

    suspend fun getWeatherAndForecast(lat: Double,
                                      long: Double,
                                      appId: String,
                                      units: String,
                                      lang: String) : WeatherForecastEntity =
        remoteDataBase.getWeatherForecastByCoordinates(lat, long, appId, units, lang)
}