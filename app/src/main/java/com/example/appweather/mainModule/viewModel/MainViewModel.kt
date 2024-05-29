package com.example.appweather.mainModule.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appweather.R
import com.example.appweather.common.entities.WeatherForecastEntity
import com.example.appweather.mainModule.model.MainRepository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    //instanciamos sin definir el tipo
    private val repository = MainRepository()

    /*LiveData refleja los cambios en la consulta genereal de la DB como
     mostrar mensajes y ayudar en la vista del progres bar*/
    private val result = MutableLiveData<WeatherForecastEntity>()
    /*Metodo publico de result = MutableLiveData<WeatherForecastEntity>()*/
    fun getResult(): LiveData<WeatherForecastEntity> = result

    private val snackbarMessage = MutableLiveData<Int>()
    fun getSnackbarMessage() = snackbarMessage

    private val loaded = MutableLiveData<Boolean>()
    fun isLoaded() = loaded

    suspend fun getWeatherAndForecast(lat: Double, lon: Double, appId: String, units: String, lang: String)
    {
        viewModelScope.launch {
            try {
                loaded.value = false //config del progres bar visible
                val resultServer =
                    repository.getWeatherAndForecast(lat, lon, appId, units, lang)
                result.value = resultServer
            } catch (e: Exception) {
                snackbarMessage.value = R.string.main_error_server
            } finally {
                //independientemente si fue exito o error hacer invisible PROGRES BAR
                loaded.value = true
            }
        }
    }
}










