package com.example.appweather.mainModule.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appweather.BR
import com.example.appweather.R
import com.example.appweather.common.entities.Forecast
import com.example.appweather.common.utils.CommonUtils
import com.example.appweather.databinding.ActivityMainBinding
import com.example.appweather.mainModule.view.adapters.ForecastAdapter
import com.example.appweather.mainModule.view.adapters.OnClickListener
import com.example.appweather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding
    //variable que ayuda con el adapter
    private lateinit var adapter: ForecastAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()
        //configurar adapter con el recyclerview
        setupAdapter()
        setupRecyclerView()
/*
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
    }


    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()

        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm) //variable de dataBinding conectado con activity_main.xml
    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackbarMessage().observe(this){resMsg -> //esto apunta al mensaje de error MainViewModel
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
            //rellenar adapter
            it.getResult().observe(this){result ->
                adapter.submitList(result.hourly) //houerly viene de WeatherForecastEntity tiene una List
            }
        }
    }

    private fun setupAdapter() {
        adapter = ForecastAdapter(this)
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            binding.viewModel?.getWeatherAndForecast(-38.4161, -63.6167,
                                                "90970b95ee4d5f526d63015fa67a6772",
                                                "metric","es")
        }
    }

    //Metodo sobre escrito de OnClickListener
    override fun onClick(forecast: Forecast) {
        //ahora solo va a mostart un mensaje
        Snackbar.make(binding.root, CommonUtils.getFullDate(forecast.dt), Snackbar.LENGTH_LONG).show()
    }
}















