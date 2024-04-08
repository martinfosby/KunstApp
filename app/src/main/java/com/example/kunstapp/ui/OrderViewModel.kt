package com.example.kunstapp.ui

import androidx.lifecycle.ViewModel
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.data.Photo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class OrderViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()


    fun updatePhoto(photo: Photo) {
        _uiState.update {
            it.copy(
                currentPhoto = photo
            )
        }
    }

    fun addToShopping(photo: Photo) {
//        _uiState.update {current_state ->
//            current_state.copy(
//                shoppingCart = current_state.shoppingCart._
//            )
//        }
        _uiState.value.shoppingCart.add(photo)
    }

}