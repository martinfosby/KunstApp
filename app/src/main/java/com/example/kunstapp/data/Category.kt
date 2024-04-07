package com.example.kunstapp.data

enum class Category(
    val id: Long,
    val totalPhotos: Int = 0,
    val mostExpensivePhoto: Photo? = null,
    val mostPopularPhoto: Photo? = null
) {
    Nature(id = 0),
    Food(id = 1),
    Sport(id =  2),
    Misc(id = 3),
    Buildings(id = 4),
}
