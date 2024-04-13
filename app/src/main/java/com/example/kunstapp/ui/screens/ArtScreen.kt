package com.example.kunstapp.ui.screens

import androidx.annotation.StringRes
import com.example.kunstapp.R

enum class ArtScreen(@StringRes var title: Int) {
    Start(title = R.string.app_name),
    Artists(title = R.string.artists),
    Categories(title = R.string.categories),
    Photos(title = R.string.photos),
    Summary(title = R.string.photo_choosen),
    Checkout(title = R.string.checkout);
}