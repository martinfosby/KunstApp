package com.example.kunstapp.ui

import android.media.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.ui.screens.ArtistScreen
import com.example.kunstapp.ui.screens.ArtScreen
import com.example.kunstapp.ui.screens.CategoryScreen
import com.example.kunstapp.ui.screens.CheckoutScreen
import com.example.kunstapp.ui.screens.PhotoScreen
import com.example.kunstapp.ui.screens.StartOrderScreen
import com.example.kunstapp.ui.screens.SummaryScreen
import com.example.kunstapp.ui.theme.logo_color_background


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtAppBar(
    currentScreen: ArtScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    onLogoClicked: () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    TopAppBar(
        title = { Text(stringResource(id = currentScreen.title)) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        },
        actions = {
            if (currentScreen == ArtScreen.Start) {
                IconButton(onClick = onLogoClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = stringResource(
                            id = R.string.app_name
                        ),
                        tint = Color.Black,
                        modifier = Modifier.background(logo_color_background)
                    )
                }
            } else {
                IconButton(onClick = onLogoClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_home_24),
                        contentDescription = stringResource(
                            id = R.string.home
                        ),
                    )
                }
            }
        }
    )
}


@Composable
fun ArtApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen =
        ArtScreen.valueOf(backStackEntry?.destination?.route ?: ArtScreen.Start.name)

    Scaffold(
        topBar = {
            ArtAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() },
                onLogoClicked = { navController.navigate(ArtScreen.Start.name) }
            )
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = ArtScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ArtScreen.Start.name) {
                StartOrderScreen(
                    orderUiState = uiState,
                    shoppingCartEmpty = uiState.shoppingCartEmpty,
                    onArtistButtonClicked = {
                        navController.navigate(ArtScreen.Artists.name)
                    },
                    onCategoryButtonClicked = {
                        navController.navigate(ArtScreen.Categories.name)
                    },
                    onCheckoutButtonClicked = {
                        viewModel.setPrice()
                        navController.navigate(ArtScreen.Checkout.name)
                    },
                    onDeleteButtonClicked = {
                        viewModel.removeFromShoppingCart(it)
                        navController.navigate(ArtScreen.Start.name)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(id = R.dimen.padding_medium))
                )
            }
            composable(route = ArtScreen.Artists.name) {
                ArtistScreen(
                    onArtistClicked = {
                        ArtScreen.Photos.title = it.nameResId
                        viewModel.setArtist(it)
                        viewModel.setPhotosFromArtist(it)
                        navController.navigate(ArtScreen.Photos.name)
                    }
                )
            }
            composable(route = ArtScreen.Categories.name) {
                CategoryScreen {
                    ArtScreen.Photos.title = it.desc
                    viewModel.setPhotosFromCategory(it)
                    navController.navigate(ArtScreen.Photos.name)
                }
            }
            composable(route = ArtScreen.Photos.name) {
                PhotoScreen(
                    orderUiState = uiState,
                    onPhotoClicked = {
                        viewModel.setPhoto(it)
                        navController.navigate(ArtScreen.Summary.name)
                    },
                    onHomeClicked = {
                        navController.navigate(ArtScreen.Start.name)
                    },
                )
            }
            composable(route = ArtScreen.Summary.name) {
                SummaryScreen(
                    orderUiState = uiState,
                    onCheckoutClicked = {
                        viewModel.addToShopping()
                        viewModel.setPrice()
                        navController.navigate(ArtScreen.Photos.name)
                    },
                    onHomeClicked = {
                        navController.navigate(ArtScreen.Start.name)
                    },
                    onFrameSelected = {
                        viewModel.setFrame(it)
                    },
                    onSizeSelected = {
                        viewModel.setSize(it)
                    },
                    onWidthSelected = {
                        viewModel.setWidth(it)
                    }
                )
            }
            composable(route = ArtScreen.Checkout.name) {
                CheckoutScreen(
                    orderUiState = uiState,
                    onPayClicked = {
                        viewModel.resetOrder()
                        navController.navigate(ArtScreen.Start.name)
                    }
                )
            }

        }
    }
}