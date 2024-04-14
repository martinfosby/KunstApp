package com.example.kunstapp

import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Frame
import com.example.kunstapp.model.Size
import com.example.kunstapp.model.Width
import com.example.kunstapp.ui.OrderViewModel
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class KunstAppUnitTest {
    private lateinit var viewmodel: OrderViewModel

    @Before
    fun setUp() {
        viewmodel = OrderViewModel()
    }

    @Test
    fun test_currentPhoto() {
        // Arrange
        val expectedPhoto = DataSource.photos[0]

        // Act
        viewmodel.setPhoto(expectedPhoto)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedPhoto, currentPhoto)
    }

    @Test
    fun test_currentPhoto_visitsIncrement() {
        // Arrange
        val expectedPhoto = DataSource.photos[0]
        val expectedVisits = 10001

        // Act
        viewmodel.setPhoto(expectedPhoto)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedVisits, currentPhoto.visits)
    }

    @Test
    fun test_addFrame_checkPrice() {
        // Arrange
        val expectedPrice = 899f
        viewmodel.setPhoto(DataSource.photos[0])

        // Act
        viewmodel.setFrame(Frame.Metal)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedPrice, currentPhoto.getTotalPrice(), 0.01f) // Specify delta for float comparison
    }

    @Test
    fun test_addSize_checkPrice() {
        // Arrange
        val expectedPrice = 799f
        viewmodel.setPhoto(DataSource.photos[0])

        // Act
        viewmodel.setSize(Size.ExtraLarge)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedPrice, currentPhoto.getTotalPrice(), 0.01f) // Specify delta for float comparison
    }

    @Test
    fun test_setSizeAndSetFrame_checkPrice() {
        // Arrange
        val expectedPrice = 899f
        viewmodel.setPhoto(DataSource.photos[0])

        // Act
        viewmodel.setSize(Size.ExtraLarge)
        viewmodel.setFrame(Frame.Wood)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedPrice, currentPhoto.getTotalPrice(), 0.01f) // Specify delta for float comparison
    }

    @Test
    fun test_setSizeAndSetFrameAndSetWidth_checkPrice() {
        // Arrange
        val expectedPrice = 1049f
        viewmodel.setPhoto(DataSource.photos[0])

        // Act
        viewmodel.setSize(Size.ExtraLarge)
        viewmodel.setFrame(Frame.Wood)
        viewmodel.setWidth(Width.Large)

        // Assert
        val currentPhoto = viewmodel.uiState.value.currentPhoto
        assertEquals(expectedPrice, currentPhoto.getTotalPrice(), 0.01f) // Specify delta for float comparison
    }


    @Test
    fun test_shoppingCart_price() {
        val expectedPrice = 1426f
        viewmodel.setPhoto(DataSource.photos[0])
        viewmodel.addToShopping()
        viewmodel.setPhoto(DataSource.photos[1])
        viewmodel.addToShopping()
        viewmodel.setPhoto(DataSource.photos[2])
        viewmodel.addToShopping()
        viewmodel.setPhoto(DataSource.photos[3])
        viewmodel.addToShopping()

        assertEquals(expectedPrice, viewmodel.uiState.value.price, 0.01f) // Specify delta for float comparison
    }
}