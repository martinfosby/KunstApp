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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.ui.theme.KunstAppTheme
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.data.Photo
import com.example.kunstapp.datasource.DataSource
import java.util.zip.CheckedOutputStream

/**
 * This composable expects [orderUiState] that represents the order state, [onCancelButtonClicked]
 * lambda that triggers canceling the order and passes the final order to [onSendButtonClicked]
 * lambda
 * */
@Composable
fun SummaryScreen(
    photo: Photo,
    onCheckoutClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PhotoCard(photo = photo)
        Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_medium)))
        CheckoutCart(photo = photo, onCheckoutClicked = onCheckoutClicked)
    }
}

@Composable
fun PhotoCard(photo: Photo, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(dimensionResource(id = R.dimen.padding_small)) // Add padding to the card
    ) {
        Box(
        ) {
            Image(
                painter = painterResource(id = photo.imageResId),
                contentDescription = null, // Provide a meaningful description for accessibility
            )
        }
    }
}

@Composable
fun CheckoutCart(
    photo: Photo,
    onCheckoutClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card {
        Column {
            Card {
                Text(text = "Bildedetaljer")
                Text(text = "Artist: ${stringResource(id = photo.artist.nameResId)}")
            }
            Card {
                Text(text = "Rammevalg")

            }
            Card {
                Text(text = "Beregnet pris")
                Text(text = "${photo.price}")
            }
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
            Button(onClick = onCheckoutClicked ) {
                Text(text = "Legg i handlekurv")
            }
        }
    }
}

@Preview
@Composable
fun SummaryPreview() {
    KunstAppTheme {
        SummaryScreen(
            photo = DataSource.photos[0],
            onCheckoutClicked = {},
            modifier = Modifier.fillMaxHeight()
        )
    }
}
