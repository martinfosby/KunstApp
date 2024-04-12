package com.example.kunstapp.data

import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Artist
import com.example.kunstapp.model.Category
import com.example.kunstapp.model.Frame
import com.example.kunstapp.model.Photo
import com.example.kunstapp.model.Size
import com.example.kunstapp.model.Width

data class OrderUiState(
    val currentPhoto: Photo = DataSource.photos[0],
    val currentArtist: Artist = DataSource.artists[0],
    val currentCategory: Category = Category.Misc,
    val currentPhotos: List<Photo> = DataSource.photos.filter { it.artist == DataSource.artists[0] },
    val shoppingCartEmpty: Boolean = true,
    val shoppingCart: MutableList<Photo> = mutableListOf(),
    /** Selected photo quantity (1, 6, 12) */
    val quantity: Int = 0,
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: Float = currentPhoto.price,
    val currentFrame: Frame = currentPhoto.frame,
    val currentSize: Size = currentPhoto.size,
    val currentWidth: Width = currentPhoto.width,
    /** Available pickup dates for the order*/
    val pickupOptions: List<String> = listOf()
)
