package com.example.kunstapp.model

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
) {
    fun getTotalPhotos(): Int {
        return DataSource.getPhotosByArtist(this).size
    }

    fun getMostExpensivePhoto(): Photo? {
        return DataSource.getPhotosByArtist(this).maxByOrNull { it.price }
    }

    fun getMostPopularPhoto(): Photo? {
        return DataSource.getPhotosByArtist(this).maxByOrNull { it.visits }
    }
}