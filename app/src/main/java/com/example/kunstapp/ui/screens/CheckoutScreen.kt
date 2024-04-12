package com.example.kunstapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun CheckoutScreen(orderUiState: OrderUiState, modifier: Modifier = Modifier) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            items(orderUiState.shoppingCart) {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.small_elevation)),
                    border = BorderStroke(width = dimensionResource(id = R.dimen.small_width),MaterialTheme.colorScheme.tertiary),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.small_width))
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_small))
                            .fillMaxWidth()
                    ) {
                        Text(text = stringResource(id = it.title), style = MaterialTheme.typography.labelLarge, modifier = Modifier.align(Alignment.CenterHorizontally))
                        Text(text = stringResource(id = R.string.calculated_price, it.getTotalPrice()), style = MaterialTheme.typography.headlineSmall)
                        Text(
                            text = stringResource(
                                id = R.string.artist_name,
                                stringResource(id = it.artist.nameResId)
                            )
                        )
                        Text(text = stringResource(id = it.size.titleResId))
                        Text(text = stringResource(id = it.frame.title))
//                            Text(text = stringResource(id = it.frame.color.value.toInt()))
                        Text(text = stringResource(id = it.frame.width.title))
                    }

                }
            }
        }
        Column {
            Text(text = stringResource(id = R.string.total_price_with_mva, orderUiState.mvaPrice), style = MaterialTheme.typography.headlineSmall)
        }

    }
}


@Preview
@Composable
fun PreviewCheckoutScreen() {
    KunstAppTheme {
        CheckoutScreen(
            orderUiState = OrderUiState(
                shoppingCart = mutableListOf(DataSource.photos[0], DataSource.photos[1])
            )
        )
    }
}