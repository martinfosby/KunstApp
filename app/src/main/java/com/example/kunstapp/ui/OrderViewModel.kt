package com.example.kunstapp.ui

import androidx.lifecycle.ViewModel
import com.example.kunstapp.model.Artist
import com.example.kunstapp.model.Category
import com.example.kunstapp.model.Frame
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.model.Photo
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Size
import com.example.kunstapp.model.Width
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()




    fun setPhoto(photo: Photo) {
        _uiState.update {
            it.copy(
                currentPhoto = photo,
                price = photo.price + it.currentFrame.price + it.currentSize.price + it.currentWidth.price
            )
        }
    }

    fun addToShopping() {
        _uiState.update {
            it.copy(
                shoppingCart = it.shoppingCart.apply {
                    add(_uiState.value.currentPhoto)
                },
                shoppingCartEmpty = it.shoppingCart.isEmpty()
            )
        }
    }



    fun removeFromShoppingCart(photo: Photo) {
        _uiState.update {
            it.copy(
                shoppingCart = it.shoppingCart.apply {
                    remove(photo)
                },
                shoppingCartEmpty = it.shoppingCart.isEmpty()
            )
        }
    }

    fun setArtist(artist: Artist) {
        _uiState.update {
            it.copy(
                currentArtist = artist
            )
        }
    }

    fun setCategory(category: Category) {
        _uiState.update {
            it.copy(
                currentCategory = category
            )
        }
    }

    fun setPhotosFromArtist(artist: Artist) {
        _uiState.update { currentState ->
            currentState.copy(
                currentPhotos = DataSource.photos.filter { it.artist.id == artist.id }
            )
        }
    }

    fun setPhotosFromCategory(category: Category) {
        _uiState.update { currentState ->
            currentState.copy(
                currentPhotos = DataSource.photos.filter { it.category.id == category.id }
            )
        }

    }
    fun setPrice() {
        _uiState.update {
            it.copy(
                price = it.currentPhoto.price + it.currentFrame.price + it.currentSize.price
            )
        }
    }

    fun setFrame(frame: Frame) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(frame = frame),
                currentFrame = frame,
                price = it.currentPhoto.price + frame.price + it.currentSize.price + it.currentWidth.price
            )
        }
    }

    fun setSize(size: Size) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(size = size),
                currentSize = size,
                price = it.currentPhoto.price + it.currentFrame.price + size.price + it.currentWidth.price
            )
        }
    }

    fun setWidth(width: Width) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(width = width),
                currentWidth = width,
                price = it.currentPhoto.price + it.currentFrame.price + it.currentSize.price + width.price
            )
        }
    }



}