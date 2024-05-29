package com.example.appweather.common.dataAccess

import com.example.appweather.common.entities.WeatherForecastEntity
import com.example.appweather.common.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET(Constants.ONE_CALL_PATH)
    suspend fun getWeatherForecastByCoordinates(
        //pasar parametros
        @Query (Constants.LATITUDE_PARAM) lat: Double,
        @Query (Constants.LATITUDE_PARAM) long: Double,
        @Query (Constants.APP_ID_PARAM) appId: String,
        @Query (Constants.UNITS_PARAM) units: String,
        @Query (Constants.LANGUAGE_PARAM) lang: String
    ) : WeatherForecastEntity
}