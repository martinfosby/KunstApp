package com.example.kunstapp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kunstapp.datasource.DataSource

@Entity(tableName = "artist")
data class Artist(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @StringRes val nameResId: Int,
    @DrawableRes val imageResId: Int,
    val totalPhotos: Int = 0,
    val mostExpensivePhoto: Photo? = null,
    val mostPopularPhoto: Photo? = null
)