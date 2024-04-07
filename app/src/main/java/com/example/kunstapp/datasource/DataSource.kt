package com.example.kunstapp.datasource

import com.example.kunstapp.R
import com.example.kunstapp.data.Artist
import com.example.kunstapp.data.Category
import com.example.kunstapp.data.Photo

object DataSource {
    val artists = listOf(
        Artist(
            id = 0,
            nameResId = R.string.artist_name_ola,
            imageResId = R.drawable.android_superhero1,
            totalPhotos = 5,
        ),
        Artist(
            id = 1,
            nameResId = R.string.artist_name_kari,
            imageResId = R.drawable.android_superhero2,
            totalPhotos = 3,
        ),
    )

    val photos = listOf(
        Photo(
            id = 0,
            title = R.string.skyscraper,
            imageResId = R.drawable.lunch_atop_a_skyscraper___charles_clyde_ebbets,
            artist = artists[0],
            category = Category.Buildings,
            price = 599f,
            visits = 1000
        )
    )

}
