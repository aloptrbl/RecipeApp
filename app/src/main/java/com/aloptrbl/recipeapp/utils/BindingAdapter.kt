package com.aloptrbl.recipeapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("imageUrl")
    fun ImageView.bindImage(url: String?) {
        load(url)
    }
}