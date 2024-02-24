package com.hyundai.myexperience.ui.notice

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imageUrl")
fun setImageUrl(view: ImageView, imageUrl: String) {
    view.load(imageUrl)
}