package com.example.appweather.common.utils

import com.example.appweather.common.entities.Weather
import java.text.SimpleDateFormat
import java.util.Locale

object CommonUtils { //epoch ES UN ESTANDAR PARA MANEJAR LA FECHA Y HORA
    fun getHour(epoch: Long) : String = getFormatedTime(epoch, "HH:mm")

    private fun getFormatedTime(epoch: Long, pattan: String): String {
        return SimpleDateFormat(pattan, Locale.getDefault()).format(epoch * 1_000)
    }

    fun getWeatherMain(weather: List<Weather>?) : String {
        return if (weather != null && weather.isNotEmpty()) weather[0].main else "_"
    }
    fun getWeatherDescription(weather: List<Weather>?) : String {
        return if (weather != null && weather.isNotEmpty()) weather[0].description else "_"
    }
}