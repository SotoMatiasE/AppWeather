package com.example.appweather.mainModule.view.adapters

import com.example.appweather.common.entities.Forecast

interface OnClickListener {

    fun onClick(forecast: Forecast)
}