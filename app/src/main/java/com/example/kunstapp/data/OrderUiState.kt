package com.example.kunstapp.data

import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Artist
import com.example.kunstapp.model.Category
import com.example.kunstapp.model.Photo

data class OrderUiState(
    val currentPhoto: Photo = DataSource.photos[0],
    val currentArtist: Artist = DataSource.artists[0],
    val currentCategory: Category = Category.Misc,
    val currentPhotos: List<Photo> = DataSource.photos.filter { it.artist == DataSource.artists[0] },
    val shoppingCartEmpty: Boolean = true,
    val shoppingCart: MutableList<Photo> = mutableListOf(),
    val quantity: Int = 0,
    /** Total price for the order */
    val price: Float = 0f,
    val mva: Float = 1.25f,
    val mvaPrice: Float = 0f,
)
