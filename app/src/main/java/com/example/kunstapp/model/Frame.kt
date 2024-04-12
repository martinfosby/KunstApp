package com.example.kunstapp.model

import androidx.annotation.StringRes
import com.example.kunstapp.R

enum class Frame(
    val price: Float,
    val width: Int,
    @StringRes val title: Int,
) {
    None(0f, 0, R.string.no_frame),
    Metal(200f, 300, R.string.metal_frame),   // Metal frame with price, width
    Wood(100f, 250, R.string.wood_frame),    // Wood frame with price, width, and height
    Plastic(50f, 200, R.string.plastic_frame)   // Plastic frame with price, width, and height
}

