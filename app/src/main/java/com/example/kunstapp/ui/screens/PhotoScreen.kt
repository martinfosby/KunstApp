package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.data.Photo
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun PhotoScreen(
    orderUiState: OrderUiState,
    onPhotoClicked: (Photo) -> Unit,
    onHomeClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
            modifier = modifier
        ) {
            items(orderUiState.currentPhotos) {photo ->
                PhotoItemCard(
                    photo = photo,
                    modifier = Modifier.clickable { onPhotoClicked(photo) }
                )
            }
        }
        Button(onClick = onHomeClicked) {
            Text(text = stringResource(id = R.string.home))
        }
    }
}


@Composable
fun PhotoItemCard(photo: Photo, modifier: Modifier = Modifier) {
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
        Text(
            text = stringResource(id = photo.title),
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .background(Color.White),
            style = MaterialTheme.typography.titleMedium
        )
    }
}


@Preview
@Composable
fun PreviewPhotoScreen() {
    KunstAppTheme {
        PhotoScreen(
            orderUiState = OrderUiState(),
            onPhotoClicked = {},
            onHomeClicked = {}
        )
    }
}
