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
            totalPhotos = 5
        ),
        Artist(
            id = 1,
            nameResId = R.string.artist_name_kari,
            imageResId = R.drawable.android_superhero2,
            totalPhotos = 3
        ),
        Artist(
            id = 2,
            nameResId = R.string.artist_name_anna,
            imageResId = R.drawable.android_superhero3,
            totalPhotos = 7
        ),
        Artist(
            id = 3,
            nameResId = R.string.artist_name_peter,
            imageResId = R.drawable.android_superhero4,
            totalPhotos = 4
        ),
        Artist(
            id = 4,
            nameResId = R.string.artist_name_john,
            imageResId = R.drawable.android_superhero5,
            totalPhotos = 6
        ),
        Artist(
            id = 5,
            nameResId = R.string.artist_name_emily,
            imageResId = R.drawable.android_superhero6,
            totalPhotos = 2
        ),
        Artist(
            id = 6,
            nameResId = R.string.artist_name_david,
            imageResId = R.drawable.bella,
            totalPhotos = 9
        ),
        Artist(
            id = 7,
            nameResId = R.string.artist_name_sarah,
            imageResId = R.drawable.faye,
            totalPhotos = 3
        ),
        Artist(
            id = 8,
            nameResId = R.string.artist_name_michael,
            imageResId = R.drawable.frankie,
            totalPhotos = 5
        ),
        Artist(
            id = 9,
            nameResId = R.string.artist_name_lisa,
            imageResId = R.drawable.koda,
            totalPhotos = 4
        ),
        Artist(
            id = 10,
            nameResId = R.string.artist_name_jens,
            imageResId = R.drawable.leroy,
            totalPhotos = 9
        ),
        Artist(
            id = 11,
            nameResId = R.string.artist_name_arnold,
            imageResId = R.drawable.lola,
            totalPhotos = 3
        ),
        Artist(
            id = 12,
            nameResId = R.string.artist_name_jonas,
            imageResId = R.drawable.nox,
            totalPhotos = 5
        ),
        Artist(
            id = 13,
            nameResId = R.string.artist_name_superman,
            imageResId = R.drawable.tzeitel,
            totalPhotos = 4
        )
    )


    val photos = listOf(
        Photo(
            id = 1,
            title = R.string.skyscraper,
            imageResId = R.drawable.lunch_atop_a_skyscraper___charles_clyde_ebbets,
            artist = artists[0],
            category = Category.Buildings,
            price = 599f,
            visits = 10000
        ),
        Photo(
            id = 2,
            title = R.string.photo1_title,
            imageResId = R.drawable.image1,
            artist = artists[1],
            category = Category.Nature,
            price = 199f,
            visits = 3000
        ),
        Photo(
            id = 3,
            title = R.string.photo2_title,
            imageResId = R.drawable.image2,
            artist = artists[2],
            category = Category.Nature,
            price = 149f,
            visits = 5000
        ),
        Photo(
            id = 4,
            title = R.string.photo3_title,
            imageResId = R.drawable.image3,
            artist = artists[3],
            category = Category.Nature,
            price = 79f,
            visits = 2500
        ),
        Photo(
            id = 5,
            title = R.string.photo4_title,
            imageResId = R.drawable.image4,
            artist = artists[4],
            category = Category.Nature,
            price = 129f,
            visits = 4000
        ),
        Photo(
            id = 6,
            title = R.string.photo5_title,
            imageResId = R.drawable.image5,
            artist = artists[5],
            category = Category.Nature,
            price = 89f,
            visits = 1500
        ),
        Photo(
            id = 7,
            title = R.string.photo6_title,
            imageResId = R.drawable.image6,
            artist = artists[6],
            category = Category.Nature,
            price = 169f,
            visits = 6000
        ),
        Photo(
            id = 8,
            title = R.string.photo7_title,
            imageResId = R.drawable.image7,
            artist = artists[7],
            category = Category.Nature,
            price = 99f,
            visits = 3500
        ),
        Photo(
            id = 9,
            title = R.string.photo8_title,
            imageResId = R.drawable.image8,
            artist = artists[8],
            category = Category.Nature,
            price = 119f,
            visits = 2000
        ),
        Photo(
            id = 10,
            title = R.string.photo9_title,
            imageResId = R.drawable.image9,
            artist = artists[9],
            category = Category.Nature,
            price = 149f,
            visits = 4500
        ),
        Photo(
            id = 11,
            title = R.string.photo10_title,
            imageResId = R.drawable.image10,
            artist = artists[10],
            category = Category.Nature,
            price = 79f,
            visits = 2800
        )
    )

}
