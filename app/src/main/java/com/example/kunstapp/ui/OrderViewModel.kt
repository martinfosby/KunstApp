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

class OrderViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    fun setPhoto(photo: Photo) {
        photo.visits += 1
        _uiState.update {
            it.copy(
                currentPhoto = photo,
            )
        }
    }


    fun setQuantity() {
        _uiState.update {
            it.copy(
                quantity = it.shoppingCart.size
            )
        }
    }

    fun addToShopping() {
        _uiState.update {
            it.copy(
                shoppingCart = it.shoppingCart.apply {
                    add(_uiState.value.currentPhoto)
                },
                shoppingCartEmpty = it.shoppingCart.isEmpty(),
                quantity = it.quantity + 1,
                price = it.price + _uiState.value.currentPhoto.getTotalPrice(),
                mvaPrice = (it.price + _uiState.value.currentPhoto.getTotalPrice()) * it.mva
            )
        }
    }


    fun removeFromShoppingCart(photo: Photo) {
        _uiState.update {
            it.copy(
                shoppingCart = it.shoppingCart.apply {
                    remove(photo)
                },
                shoppingCartEmpty = it.shoppingCart.isEmpty(),
                quantity = it.quantity - 1,
                price = it.price - photo.getTotalPrice(),
                mvaPrice = (it.price - photo.getTotalPrice()) * it.mva
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
        _uiState.update { currentState ->
            var totalPrice = 0.0f
            currentState.shoppingCart.forEach { totalPrice += it.getTotalPrice() }
            currentState.copy(
                price = totalPrice,
                mvaPrice = totalPrice * currentState.mva
            )
        }
    }


    fun setFrame(frame: Frame) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(
                    frame = frame,
                ),
            )
        }
    }

    fun setSize(size: Size) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(
                    size = size,
                ),
            )
        }
    }

    fun setWidth(width: Width) {
        _uiState.update {
            it.copy(
                currentPhoto = it.currentPhoto.copy(
                    width = width,
                ),
            )
        }
    }


}