package com.example.kunstapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.ReturnThis
import androidx.annotation.StringRes
import com.example.kunstapp.R
import com.example.kunstapp.datasource.DataSource

enum class Category(
    val id: Long,
    @StringRes val desc: Int,
    @DrawableRes val imageResId: Int,
) {
    Nature(id = 0, desc = R.string.nature, imageResId = R.drawable.baseline_flutter_dash_24),
    Architecture(id = 1, desc = R.string.architecture, imageResId = R.drawable.baseline_architecture_24),
    Sport(id = 2, desc = R.string.sport, imageResId = R.drawable.baseline_sports_volleyball_24),
    Misc(id = 3, desc = R.string.misc, imageResId = R.drawable.baseline_handyman_24),
    Buildings(id = 4, desc = R.string.Buildings, imageResId = R.drawable.baseline_holiday_village_24),
    Food(id = 5, desc = R.string.food, imageResId = R.drawable.baseline_fastfood_24);

    fun getTotalPhotos(): Int {
        return DataSource.getPhotosByCategory(this).size
    }

    fun getMostExpensivePhoto(): Photo? {
        return DataSource.getPhotosByCategory(this).maxByOrNull { it.price }
    }

    fun getMostPopularPhoto(): Photo? {
        return DataSource.getPhotosByCategory(this).maxByOrNull { it.visits }
    }
}
