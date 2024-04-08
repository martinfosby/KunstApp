package com.example.kunstapp.data

data class OrderUiState(
    val currentPhoto: Photo? = null,
    val checkout: Boolean = false,
    val shoppingCart: MutableList<Photo> = mutableListOf(),
    /** Selected photo quantity (1, 6, 12) */
    val quantity: Int = 0,
    /** Selected date for pickup (such as "Jan 1") */
    val date: String = "",
    /** Total price for the order */
    val price: String = "",
    /** Available pickup dates for the order*/
    val pickupOptions: List<String> = listOf()
)
