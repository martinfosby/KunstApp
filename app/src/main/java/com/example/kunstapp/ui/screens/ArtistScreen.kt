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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.model.Artist
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun ArtistScreen(
    modifier: Modifier = Modifier,
    onArtistClicked: (Artist) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        items(DataSource.artists){ artist ->
            ArtistListItem(
                artist = artist,
                modifier = Modifier
                    .clickable(onClick = { onArtistClicked(artist) })
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}


@Composable
fun ArtistListItem(
    modifier: Modifier = Modifier,
    artist: Artist,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(id = R.dimen.small_elevation)),
        modifier = modifier,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .sizeIn(minHeight = dimensionResource(id = R.dimen.photo_extra_small))
        ) {
            Box(
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.photo_extra_small))
                    .clip(RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)))

            ) {
                Image(
                    painter = painterResource(artist.imageResId),
                    contentDescription = null,
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(Modifier.width(dimensionResource(id = R.dimen.padding_large)))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = stringResource(artist.nameResId),
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = stringResource(
                        id = R.string.total_photos,
                        artist.getTotalPhotos()
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                artist.getMostExpensivePhoto()?.let { mostExpensivePhoto ->
                    Text(
                        text = stringResource(
                            id = R.string.most_expensive_photo,
                            stringResource(id = mostExpensivePhoto.title)
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                artist.getMostPopularPhoto()?.let { mostPopularPhoto ->
                    Text(
                        text = stringResource(
                            id = R.string.most_popular_photo,
                            stringResource(id = mostPopularPhoto.title)
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

        }
    }
}


@Preview
@Composable
fun PreviewArtistScreen() {
    KunstAppTheme {
        ArtistScreen(onArtistClicked = {})
    }
}
