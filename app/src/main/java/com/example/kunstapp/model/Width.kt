package com.example.kunstapp.model

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.example.kunstapp.R

enum class Width(
    @StringRes val title: Int,
    @DimenRes val width: Int,
    val price: Float
) {
    None(R.string.no_width, R.dimen.no_width, 0f),
    Small(R.string.small_width, R.dimen.small_width, 50f),
    Medium(R.string.medium_width, R.dimen.medium_width, 100f),
    Large(R.string.large_width, R.dimen.large_width, 150f)
}