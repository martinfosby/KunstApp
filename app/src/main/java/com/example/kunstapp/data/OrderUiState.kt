package com.example.kunstapp.data

import com.example.kunstapp.datasource.DataSource

data class OrderUiState(
    val currentPhoto: Photo = DataSource.photos[0],
    val currentArtist: Artist = DataSource.artists[0],
    val currentPhotos: List<Photo> = DataSource.photos.filter { it.artist == DataSource.artists[0] },
    val shoppingCartEmpty: Boolean = true,
    val shoppingCart: MutableList<Photo> = mutableListOf(),
    /** Selected photo quantity (1, 6, 12) */
    val quantity: Int = 0,
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: Float = currentPhoto.price,
    /** Available pickup dates for the order*/
    val pickupOptions: List<String> = listOf()
)
