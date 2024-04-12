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
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: Float = currentPhoto.price,
    /** Available pickup dates for the order*/
    val pickupOptions: List<String> = listOf()
)
