package com.example.kunstapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.kunstapp.ui.ArtApp
import com.example.kunstapp.ui.screens.ArtScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtScreenNavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setupArtAppNavHost() {
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
            }
            ArtApp(navController = navController)
        }
    }


    @Test
    fun artApp_verifyStartDestination() {
        navController.assertCurrentRouteName(ArtScreen.Start.name)
    }

    @Test
    fun artApp_verifyBackNavigationNotShownOnStartOrderScreen() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).assertDoesNotExist()
    }

    @Test
    fun artApp_clickBackNavigationFromArtistScreen_navigatesToStartScreen() {
        navigateToArtistScreen()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
        navController.assertCurrentRouteName(ArtScreen.Start.name)
    }

    @Test
    fun artApp_clickBackNavigationFromPhotoScreen_navigatesToArtistScreen() {
        navigateToPhotoScreenFromArtistScreen()
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
        navController.assertCurrentRouteName(ArtScreen.Artists.name)
    }

    @Test
    fun artApp_clickBackNavigationFromCustomPhotoScreen_navigatesToPhotoScreen() {
        navigateToCustomPhotoScreen()
        performNavigateUp()
        navController.assertCurrentRouteName(ArtScreen.Photos.name)
    }

    @Test
    fun artApp_clickAddToShoppingCartFromCustomPhotoScreen_navigatesToPhotoScreen() {
        navigateToCustomPhotoScreen()
        composeTestRule.onNodeWithStringId(R.string.put_in_shopping_cart).performClick()
        navController.assertCurrentRouteName(ArtScreen.Photos.name)
    }

    @Test
    fun artApp_clickGoToCheckoutFromStartOrderScreen_navigatesToCheckoutScreen() {
        navigateToPhotoScreenFromCustomPhotoScreen()
        composeTestRule.onNodeWithStringId(R.string.skyscraper)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.home).performClick()
        composeTestRule.onNodeWithStringId(R.string.go_to_checkout).performClick()
        navController.assertCurrentRouteName(ArtScreen.Checkout.name)
    }

    @Test
    fun artApp_verifyCustomPhotoScreen() {
        navigateToCustomPhotoScreen()
        navController.assertCurrentRouteName(ArtScreen.Customize.name)
    }

    @Test
    fun artApp_verifyCheckoutScreen() {
        navigateToCheckoutScreen()
        navController.assertCurrentRouteName(ArtScreen.Checkout.name)
    }


    @Test
    fun artApp_clickOnPayAndOk_navigateToStartScreen() {
        navigateToCheckoutScreen()
        composeTestRule.onNodeWithStringId(R.string.pay)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.ok)
            .performClick()
        navController.assertCurrentRouteName(ArtScreen.Start.name)
    }



    private fun navigateToArtistScreen() {
        composeTestRule.onNodeWithStringId(R.string.artist)
            .performClick()
    }

    private fun navigateToPhotoScreenFromArtistScreen() {
        navigateToArtistScreen()
        composeTestRule.onNodeWithStringId(R.string.artist_name_ola)
            .performClick()
    }

    private fun navigateToCustomPhotoScreen() {
        navigateToPhotoScreenFromArtistScreen()
        composeTestRule.onNodeWithStringId(R.string.skyscraper)
            .performClick()
    }

    private fun navigateToPhotoScreenFromCustomPhotoScreen() {
        navigateToCustomPhotoScreen()
        composeTestRule.onNodeWithStringId(R.string.put_in_shopping_cart)
            .performClick()
    }

    private fun navigateToStartOrderScreenFromCustomPhotoScreen() {
        navigateToCustomPhotoScreen()
        composeTestRule.onNodeWithStringId(R.string.home)
            .performClick()
    }


    private fun navigateToCheckoutScreen() {
        navigateToPhotoScreenFromCustomPhotoScreen()
        composeTestRule.onNodeWithStringId(R.string.skyscraper)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.home)
            .performClick()
        composeTestRule.onNodeWithStringId(R.string.go_to_checkout)
            .performClick()
    }


    private fun performNavigateUp() {
        val backText = composeTestRule.activity.getString(R.string.back_button)
        composeTestRule.onNodeWithContentDescription(backText).performClick()
    }


}