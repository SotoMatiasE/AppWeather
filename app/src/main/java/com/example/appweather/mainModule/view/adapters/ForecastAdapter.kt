package com.example.appweather.mainModule.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.appweather.BR
import com.example.appweather.R
import com.example.appweather.common.entities.Forecast
import com.example.appweather.databinding.ItemWeatherBinding

class ForecastAdapter (private val listener: OnClickListener) :
    ListAdapter<Forecast, RecyclerView.ViewHolder>(ForecastDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item_weather, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val forecast = getItem(position)

        with(holder as ViewHolder){
            holder.binding?.setVariable(BR.forecast, forecast)
            holder.binding?.executePendingBindings()

            setListener(forecast)
        }
    }

    inner class ViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val binding = DataBindingUtil.bind<ItemWeatherBinding>(view)

        fun setListener(forecast: Forecast){
            binding?.root?.setOnClickListener {
                //ejecutar on click
                listener.onClick(forecast) //muestra el snackBar de la MainActivity
            }
        }
    }

    class ForecastDiffCallback : DiffUtil.ItemCallback<Forecast>() {
        override fun areItemsTheSame(oldItem: Forecast, newItem: Forecast):
                Boolean = oldItem.dt == newItem.dt

        override fun areContentsTheSame(oldItem: Forecast, newItem: Forecast):
                Boolean = oldItem == newItem
    }
}