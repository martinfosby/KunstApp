package com.example.kunstapp.model

import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.example.kunstapp.R

enum class Size(
    @StringRes val titleResId: Int,
    @DimenRes val sizeResId: Int,
    val price: Float
) {
    Small(R.string.photo_small, R.dimen.photo_small, 50f),
    Medium(R.string.photo_medium, R.dimen.photo_medium, 100f),
    Large(R.string.photo_large, R.dimen.photo_large, 150f),
    ExtraLarge(R.string.photo_huge, R.dimen.photo_huge, 200f),
    ;
}