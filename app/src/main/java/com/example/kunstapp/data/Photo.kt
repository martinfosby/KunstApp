package com.example.kunstapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @StringRes var title: Int,
    @DrawableRes var imageResId: Int,
    var artist: Artist,
    var category: Category,
    var price: Float = 0.0f,
    var visits: Int = 0
)