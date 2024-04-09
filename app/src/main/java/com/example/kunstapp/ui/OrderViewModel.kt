package com.example.kunstapp.ui

import androidx.lifecycle.ViewModel
import com.example.kunstapp.data.Artist
import com.example.kunstapp.data.Frame
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.data.Photo
import com.example.kunstapp.datasource.DataSource
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
                currentPhoto = photo
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

    fun setFrame(frame: Frame) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(frame = frame),
                price = it.currentPhoto.price + frame.price
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

    fun setPhotosFromArtist(artist: Artist) {
        _uiState.update { currentState ->
            currentState.copy(
                currentPhotos = DataSource.photos.filter { it.artist.id == artist.id }
            )
        }
    }

}