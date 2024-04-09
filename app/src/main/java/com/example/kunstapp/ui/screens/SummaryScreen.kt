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
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
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
import com.example.kunstapp.data.Frame
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.ui.theme.KunstAppTheme
import com.example.kunstapp.data.Photo


@Composable
fun SummaryScreen(
    orderUiStateState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoCard(orderUiState = orderUiStateState)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        CheckoutCart(
                orderUiStateState = orderUiStateState,
                onCheckoutClicked = onCheckoutClicked,
                onFrameSelected = onFrameSelected
            )
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
            )
        }
    }
}

@Composable
fun CheckoutCart(
    orderUiStateState: OrderUiState,
    onCheckoutClicked: () -> Unit,
    onFrameSelected: (Frame) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card {
        Column {
            Card {
                Text(text = stringResource(id = R.string.photo_details))
                Text(text = stringResource(id = R.string.artist_name, stringResource(id = orderUiStateState.currentPhoto.artist.nameResId)))
            }
            Card {
                Text(text = stringResource(id = R.string.frame_choice))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.no_frame))
                    RadioButton(
                        selected = orderUiStateState.currentPhoto.frame == Frame.None,
                        onClick = {
                            onFrameSelected(Frame.None)
                        }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.steel_frame))
                    RadioButton(
                        selected = orderUiStateState.currentPhoto.frame == Frame.Metal,
                        onClick = {
                            onFrameSelected(Frame.Metal)
                        }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.wood_frame))
                    RadioButton(
                        selected = orderUiStateState.currentPhoto.frame == Frame.Wood,
                        onClick = {
                            onFrameSelected(Frame.Wood)
                        }
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = stringResource(id = R.string.plastic_frame))
                    RadioButton(
                        selected = orderUiStateState.currentPhoto.frame == Frame.Plastic,
                        onClick = {
                            onFrameSelected(Frame.Plastic)
                        }
                    )
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
            orderUiStateState = OrderUiState(),
            onFrameSelected = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}
