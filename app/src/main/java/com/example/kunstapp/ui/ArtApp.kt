package com.example.kunstapp.ui

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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kunstapp.R
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.screens.ArtistScreen
import com.example.kunstapp.ui.screens.ArtScreen
import com.example.kunstapp.ui.screens.CheckoutScreen
import com.example.kunstapp.ui.screens.PhotoScreen
import com.example.kunstapp.ui.screens.StartOrderScreen
import com.example.kunstapp.ui.screens.SummaryScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtAppBar(
    currentScreen: ArtScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    modifier: Modifier = Modifier
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
        }
    )
}


@Composable
fun ArtApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ArtScreen.valueOf(backStackEntry?.destination?.route ?: ArtScreen.Start.name)

    Scaffold(
        topBar = {
            ArtAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = { navController.navigateUp() }
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
                        viewModel.setArtist(it)
                        viewModel.setPhotosFromArtist(it)
                        navController.navigate(ArtScreen.Photos.name)
                    }
                )
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
                    }
                )
            }
            composable(route = ArtScreen.Summary.name) {
                SummaryScreen(
                    orderUiStateState = uiState,
                    onCheckoutClicked = {
                        viewModel.addToShopping()
                        navController.navigate(ArtScreen.Photos.name)
                    },
                    onHomeClicked = {
                        navController.navigate(ArtScreen.Start.name)
                    },
                    onFrameSelected = {
                        viewModel.setFrame(it)
                    }
                )
            }
            composable(route = ArtScreen.Checkout.name) {
                CheckoutScreen(orderUiState = uiState)
            }

        }
    }
}