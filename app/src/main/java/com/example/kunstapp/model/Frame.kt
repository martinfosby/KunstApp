package com.example.kunstapp.model

import androidx.annotation.StringRes
import com.example.kunstapp.R

enum class Frame(
    val price: Float,
    val width: Int,
    val height: Int,
    @StringRes val title: Int,
) {
    None(0f, 0,0, R.string.no_frame),
    Metal(200f, 300, 400, R.string.metal_frame),   // Metal frame with price, width, and height
    Wood(100f, 250, 300, R.string.wood_frame),    // Wood frame with price, width, and height
    Plastic(50f, 200, 300, R.string.plastic_frame)   // Plastic frame with price, width, and height
}

