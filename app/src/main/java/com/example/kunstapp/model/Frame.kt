package com.example.kunstapp.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.kunstapp.R
import com.example.kunstapp.ui.theme.frame_color_metal
import com.example.kunstapp.ui.theme.frame_color_plastic
import com.example.kunstapp.ui.theme.frame_color_wood

enum class Frame(
    var price: Float,
    @StringRes val title: Int,
    var color: Color,
    var width: Width = Width.None,
) {

    None(0f, R.string.no_frame, Color.Unspecified),
    Plastic(50f, R.string.plastic_frame, frame_color_plastic),   // Plastic frame with price, width, and height;
    Wood(100f, R.string.wood_frame, frame_color_wood),    // Wood frame with price, width, and height
    Metal(200f, R.string.metal_frame, frame_color_metal),   // Metal frame with price, width
    ;
}

