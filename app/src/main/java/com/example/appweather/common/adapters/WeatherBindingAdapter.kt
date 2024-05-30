package com.example.appweather.common.adapters

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["isGone"]) //se agrega para que pueda ser ocupado dentro d euna vista/activity_main
fun bindIsGone(view: View, isGone: Boolean) {
    //si isGone oculta si no visible
    view.visibility = if (isGone) View.GONE else View.VISIBLE
}