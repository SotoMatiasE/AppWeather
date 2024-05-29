package com.example.appweather.mainModule.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.appweather.BR
import com.example.appweather.R
import com.example.appweather.databinding.ActivityMainBinding
import com.example.appweather.mainModule.viewModel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupViewModel()
        setupObservers()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun setupViewModel() {
        val vm: MainViewModel by viewModels()

        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, vm) //vatiable de dataBinding conectado con activity_main.xml

    }

    private fun setupObservers() {
        binding.viewModel?.let {
            it.getSnackbarMessage().observe(this){resMsg -> //esto apunta al mensaje de error MainViewModel
                Snackbar.make(binding.root, resMsg, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch {
            binding.viewModel.getWeatherAndForecast(-38.9517, -68.0592,
                                                "90970b95ee4d5f526d63015fa67a6772", "metric",
                                                "sp, es")
        }
    }
}















