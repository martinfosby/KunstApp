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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.model.Frame
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Size
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun SummaryScreen(
    orderUiState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    onSizeSelected: (Size) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        PhotoCard(orderUiState = orderUiState)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        CheckoutCart(
                orderUiStateState = orderUiState,
                onCheckoutClicked = onCheckoutClicked,
                onFrameSelected = onFrameSelected,
                onSizeSelected = onSizeSelected,
            )
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        Button(onClick = onHomeClicked) {
            Text(text = stringResource(id = R.string.home))
        }
    }
}

@Composable
fun PhotoCard(orderUiState: OrderUiState, modifier: Modifier = Modifier) {
    val col = when(orderUiState.currentPhoto.frame){
        Frame.None -> Color.Transparent
        Frame.Metal -> Color.LightGray
        Frame.Wood -> Color.Yellow
        Frame.Plastic -> Color.DarkGray
    }
    Card(
        modifier = modifier
            .padding(dimensionResource(id = R.dimen.padding_small)) // Add padding to the card
    ) {
        Box(
            modifier = Modifier
                .border(border = BorderStroke(width = dimensionResource(id = R.dimen.padding_medium), color = col)
            )
        ) {
            Image(
                painter = painterResource(id = orderUiState.currentPhoto.imageResId),
                contentDescription = null, // Provide a meaningful description for accessibility
                modifier = Modifier.size(dimensionResource(id = R.dimen.photo_large))
            )
        }
    }
}

@Composable
fun CheckoutCart(
    orderUiStateState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    onSizeSelected: (Size) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedFrame by rememberSaveable { mutableStateOf(orderUiStateState.currentPhoto.frame) }
    var selectedSize by rememberSaveable { mutableStateOf(orderUiStateState.currentPhoto.size) }

    Card {
        Column {
            Card {
                Text(text = stringResource(id = R.string.photo_details))
                Text(text = stringResource(id = R.string.artist_name, stringResource(id = orderUiStateState.currentPhoto.artist.nameResId)))
            }
            Card {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Frame.entries.forEach { frame ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_small))
                                .selectable(
                                    selected = selectedFrame == frame,
                                    onClick = {
                                        selectedFrame = frame
                                        onFrameSelected(frame)
                                    }
                                ),
                        ) {
                            Text(text = stringResource(id = frame.title), style = MaterialTheme.typography.labelMedium)
                            Text(
                                text = stringResource(
                                    id = R.string.price, frame.price
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                            RadioButton(
                                selected = selectedFrame == frame,
                                onClick = {
                                    selectedFrame = frame
                                    onFrameSelected(frame)
                                }
                            )
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Size.entries.forEach { size ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen.padding_small))
                                .selectable(
                                    selected = selectedSize == size,
                                    onClick = {
                                        selectedSize = size
                                        onSizeSelected(size)
                                    }
                                ),
                        ) {
                            Text(text = stringResource(id = size.titleResId), style = MaterialTheme.typography.labelMedium)
                            Text(
                                text = stringResource(
                                    id = R.string.price, size.price
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                            RadioButton(
                                selected = selectedSize == size,
                                onClick = {
                                    selectedSize = size
                                    onSizeSelected(size)
                                }
                            )
                        }
                    }
                }

            }
            Card {
                Text(
                    text = stringResource(
                        id = R.string.calculated_price, orderUiStateState.price
                    )
                )

            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Button(onClick = onCheckoutClicked ) {
                Text(text = stringResource(id = R.string.put_in_shopping_cart))
            }
        }
    }
}

@Preview
@Composable
fun SummaryPreview() {
    KunstAppTheme {
        SummaryScreen(
            onCheckoutClicked = {},
            onHomeClicked = {},
            orderUiState = OrderUiState(
                DataSource.photos[11],
                currentPhotos = DataSource.photos.filter { it.artist.id == DataSource.artists[0].id },
                currentArtist = DataSource.artists[0] )
            ,
            onFrameSelected = {},
            onSizeSelected = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}
