package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.model.Photo
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun PhotoScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onPhotoClicked: (Photo) -> Unit,
    onHomeClicked: () -> Unit,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
    ) {
        items(orderUiState.currentPhotos) { photo ->
            PhotoItemCard(
                photo = photo,
                modifier = Modifier.clickable { onPhotoClicked(photo) }
            )
        }
        item {
            Button(onClick = onHomeClicked) {
                Text(text = stringResource(id = R.string.home))
            }
        }
    }

}


@Composable
fun PhotoItemCard(
    modifier: Modifier = Modifier,
    photo: Photo,
) {
    Box(modifier = modifier, contentAlignment = Alignment.TopCenter) {
        Image(
            painter = painterResource(id = photo.imageResId),
            contentDescription = stringResource(id = photo.title),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
                .size(dimensionResource(id = R.dimen.photo_medium))
        )
        Surface(
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.padding_small)),
            modifier = Modifier.padding(
                top = dimensionResource(
                    id = R.dimen.padding_small
                )
            )
        ) {
            Text(
                text = stringResource(id = photo.title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Clip,
                softWrap = false,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
            )
        }

    }
}


@Preview
@Composable
fun PreviewPhotoScreen() {
    KunstAppTheme {
        PhotoScreen(
            orderUiState = OrderUiState(
                DataSource.photos[0],
                currentPhotos = DataSource.photos.filter { it.artist.id == DataSource.artists[0].id },
                currentArtist = DataSource.artists[0]
            ),
            onPhotoClicked = {},
            onHomeClicked = {}
        )
    }
}
