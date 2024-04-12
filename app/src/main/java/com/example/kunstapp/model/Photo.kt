package com.example.kunstapp.model

import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kunstapp.R


@Entity(tableName = "photo")
data class Photo(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @StringRes var title: Int,
    @DrawableRes var imageResId: Int,
    var artist: Artist,
    var category: Category,
    var price: Float = 0.0f,
    var visits: Int = 0,
    var frame: Frame = Frame.None,
    var size: Size = Size.Medium,
    val width: Width = Width.None
) {
}