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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.model.Frame
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.model.Size
import com.example.kunstapp.model.Width
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun SummaryScreen(
    orderUiState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    onSizeSelected: (Size) -> Unit,
    onWidthSelected: (Width) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        PhotoBox(
            orderUiState = orderUiState,
        )
        CheckoutCart(
            orderUiStateState = orderUiState,
            onCheckoutClicked = onCheckoutClicked,
            onFrameSelected = onFrameSelected,
            onSizeSelected = onSizeSelected,
            onWidthSelected = onWidthSelected,
        )
        Button(
            onClick = onHomeClicked,
        ) {
            Text(text = stringResource(id = R.string.home))
        }
    }
}

@Composable
fun PhotoBox(orderUiState: OrderUiState, modifier: Modifier = Modifier) {

    Image(
        painter = painterResource(id = orderUiState.currentPhoto.imageResId),
        contentDescription = null, // Provide a meaningful description for accessibility
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(
                dimensionResource(id = orderUiState.currentPhoto.size.sizeResId),
            )
            .border(
                BorderStroke(
                    width = dimensionResource(id = orderUiState.currentPhoto.width.width),
                    color = orderUiState.currentPhoto.frame.color
                )
            )
            .padding(dimensionResource(id = orderUiState.currentPhoto.width.width))
//            .aspectRatio(16f/9f)
    )

}

@Composable
fun CheckoutCart(
    orderUiStateState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    onSizeSelected: (Size) -> Unit,
    onWidthSelected: (Width) -> Unit,
    modifier: Modifier = Modifier,
) {
    var selectedFrame by rememberSaveable { mutableStateOf(orderUiStateState.currentPhoto.frame) }
    var selectedSize by rememberSaveable { mutableStateOf(orderUiStateState.currentPhoto.size) }
    var selectedWidth by rememberSaveable { mutableStateOf(orderUiStateState.currentPhoto.width) }

    Card(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.photo_details),
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
            ) {
                Text(
                    text = stringResource(
                        id = R.string.artist_name,
                        stringResource(id = orderUiStateState.currentPhoto.artist.nameResId)
                    )
                )
                Text(
                    text = stringResource(
                        id = R.string.price,
                        orderUiStateState.currentPhoto.price
                    )
                )
            }
            Text(
                text = stringResource(id = R.string.frame_title),
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Frame.entries.forEach { frame ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .selectable(
                                selected = selectedFrame == frame,
                                onClick = {
                                    selectedFrame = frame
                                    onFrameSelected(frame)
                                }
                            )
                            .padding(start = dimensionResource(id = R.dimen.padding_small)),
                    ) {
                        Text(
                            text = stringResource(id = frame.title),
                            style = MaterialTheme.typography.labelMedium
                        )
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
            if (selectedFrame != Frame.None) {
                Text(
                    text = stringResource(id = R.string.frame_width),
                    style = MaterialTheme.typography.labelMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                ) {
                    Width.entries.forEach { width ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .selectable(
                                    selected = selectedWidth == width,
                                    onClick = {
                                        selectedWidth = width
                                        onWidthSelected(width)
                                    }
                                )
                                .padding(start = dimensionResource(id = R.dimen.padding_small)),
                        ) {
                            Text(
                                text = stringResource(
                                    id = R.string.width,
                                    dimensionResource(id = width.width).value.toString()
                                ), style = MaterialTheme.typography.labelMedium
                            )
                            Text(
                                text = stringResource(
                                    id = R.string.price, width.price
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                            RadioButton(
                                selected = selectedWidth == width,
                                onClick = {
                                    selectedWidth = width
                                    onWidthSelected(width)
                                }
                            )
                        }
                    }
                }
            }
            Text(
                text = stringResource(id = R.string.size_title),
                style = MaterialTheme.typography.labelMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                Size.entries.forEach { size ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .selectable(
                                selected = selectedSize == size,
                                onClick = {
                                    selectedSize = size
                                    onSizeSelected(size)
                                }
                            )
                            .padding(start = dimensionResource(id = R.dimen.padding_small)),
                    ) {
                        Text(
                            text = stringResource(id = size.titleResId),
                            style = MaterialTheme.typography.labelMedium
                        )
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
            Text(
                text = stringResource(
                    id = R.string.calculated_price, orderUiStateState.currentPhoto.getTotalPrice()
                ),
                style = MaterialTheme.typography.labelMedium
            )
            Button(
                onClick = onCheckoutClicked,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
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
                DataSource.photos[0],
                currentPhotos = DataSource.photos.filter { it.artist.id == DataSource.artists[0].id },
                currentArtist = DataSource.artists[0],
            ),
            onFrameSelected = {},
            onSizeSelected = {},
            onWidthSelected = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}
