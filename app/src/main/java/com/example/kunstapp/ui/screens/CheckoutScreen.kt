package com.example.kunstapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun CheckoutScreen(orderUiState: OrderUiState, modifier: Modifier = Modifier) {
    Column {
        Card {
            Text(text = stringResource(id = R.string.checkout))
            LazyColumn {
                items(orderUiState.shoppingCart) {
                    Card {
                        Text(text = stringResource(id = it.title))
                        Text(text = stringResource(id = R.string.calculated_price, it.price))
                        Text(text = stringResource(id = R.string.artist_name, stringResource(id = it.artist.nameResId)))
                    }
                }
            }
            Column {
                Text(text = stringResource(id = R.string.total_price, orderUiState.price))
            }

        }
    }
}


@Preview
@Composable
fun PreviewCheckoutScreen() {
    KunstAppTheme {
        CheckoutScreen(
            orderUiState = OrderUiState(
                DataSource.photos[0],
                currentPhotos = DataSource.photos.filter { it.artist.id == DataSource.artists[0].id },
                currentArtist = DataSource.artists[0] )
        )
    }
}