package com.example.kunstapp.model

import androidx.annotation.DimenRes
import com.example.kunstapp.R

enum class Width(
    @DimenRes val width: Int,
    val price: Float
) {
    None(R.dimen.no_width, 0f),
    Small(R.dimen.small_width, 50f),
    Medium(R.dimen.medium_width, 100f),
    Large(R.dimen.large_width, 150f)
}