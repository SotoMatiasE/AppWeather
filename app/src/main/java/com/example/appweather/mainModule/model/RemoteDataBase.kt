package com.example.appweather.mainModule.model

import com.example.appweather.common.dataAccess.WeatherService
import com.example.appweather.common.entities.WeatherForecastEntity
import com.example.appweather.common.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDataBase {
    //configurar RETROFIT
    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(WeatherService::class.java)
    suspend fun getWeatherForecastByCoordinates(
        lat: Double,
        lon: Double,
        appId: String,
        units: String,              ///DISPATCHER.IO es para una petision a la DB remota
        lang: String) : WeatherForecastEntity = withContext(Dispatchers.IO){
            service.getWeatherForecastByCoordinates(lat, lon, appId, units, lang)
    }
}














