/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.model.Photo
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun StartOrderScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    shoppingCartEmpty: Boolean,
    onArtistButtonClicked: () -> Unit,
    onCategoryButtonClicked: () -> Unit,
    onCheckoutButtonClicked: () -> Unit,
    onDeleteButtonClicked: (Photo) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.choose_option),
            style = MaterialTheme.typography.displayLarge
        )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Row(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
            modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_large))
        ) {
            Button(onClick = onArtistButtonClicked) {
                Text(text = stringResource(id = R.string.artist))
            }
            Button(onClick = onCategoryButtonClicked) {
                Text(text = stringResource(id = R.string.category))
            }
        }
        if (!shoppingCartEmpty) {
            Text(text = stringResource(id = R.string.total_price_with_mva, orderUiState.mvaPrice), color = Color.Green)
            Text(text = stringResource(id = R.string.quantity, orderUiState.quantity))
            ShoppingCartCard(
                orderUiState = orderUiState,
                onDeleteButtonClicked = onDeleteButtonClicked,
                onCheckoutButtonClicked = onCheckoutButtonClicked
            )

        }
    }
}

@Composable
fun ShoppingCartCard(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onDeleteButtonClicked: (Photo) -> Unit,
    onCheckoutButtonClicked: () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        LazyColumn(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(orderUiState.shoppingCart) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium))
                    ) {
                        Image(
                            painter = painterResource(id = it.imageResId),
                            contentDescription = null,
                            modifier
                                .size(dimensionResource(id = R.dimen.photo_extra_small))
                                .padding(dimensionResource(id = it.frame.width.width))
                                .border(
                                    dimensionResource(id = it.frame.width.width),
                                    color = it.frame.color
                                )

                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = stringResource(id = it.title))
                        Text(text = stringResource(id = R.string.frame, it.frame.name))
                        Text(text = stringResource(id = R.string.price, it.getTotalPrice()))
                    }
                    Button(
                        onClick = { onDeleteButtonClicked(it) },
                        modifier = Modifier
                            .align(Alignment.Top)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_delete_24),
                            contentDescription = stringResource(id = R.string.delete)
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_large)))
                Button(modifier = Modifier.align(Alignment.CenterHorizontally), onClick = onCheckoutButtonClicked) {
                    Text(text = stringResource(id = R.string.go_to_checkout))
                }
            }

        }
    }
}

@Preview
@Composable
fun StartOrderPreview() {
    KunstAppTheme {
        StartOrderScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.padding_medium)),
            orderUiState = OrderUiState(
                DataSource.photos[0],
                currentPhotos = DataSource.photos.filter { it.artist.id == DataSource.artists[0].id },
                currentArtist = DataSource.artists[0],
                shoppingCart = DataSource.photos.toMutableList()
            ),
            shoppingCartEmpty = false,
            onArtistButtonClicked = {},
            onCategoryButtonClicked = {},
            onCheckoutButtonClicked = {},
            onDeleteButtonClicked = {},
        )
    }
}
