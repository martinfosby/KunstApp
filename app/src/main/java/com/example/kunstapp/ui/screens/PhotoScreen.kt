package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kunstapp.data.Photo
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun PhotoScreen(onPhotoClicked: (Photo) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn {
        items(DataSource.photos) {photo ->
            PhotoItemCard(
                photo = photo,
                modifier = Modifier.clickable { onPhotoClicked(photo) }
            )
        }
    }
}


@Composable
fun PhotoItemCard(photo: Photo, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = photo.imageResId),
                contentDescription = stringResource(id = photo.title),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
            )
            Text(
                text = stringResource(id = photo.title),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


@Preview
@Composable
fun PreviewPhotoScreen() {
    KunstAppTheme {
        PhotoScreen(onPhotoClicked = {})
    }
}
