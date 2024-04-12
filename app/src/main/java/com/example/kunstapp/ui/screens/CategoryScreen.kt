package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.model.Category
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun CategoryScreen(modifier: Modifier = Modifier, onCategoryClicked: (Category) -> Unit) {
    LazyColumn {
        items(Category.entries) {category ->
            Card(modifier = Modifier.clickable { onCategoryClicked(category) }) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = category.imageResId),
                        contentDescription = stringResource(id = category.desc),
                        modifier = Modifier.size(dimensionResource(id = R.dimen.photo_extra_small))
                    )
                    Column {
                        Text(
                            text = stringResource(id = category.desc),
                            style = MaterialTheme.typography.labelMedium
                        )
                        Text(
                            text = stringResource(
                                id = R.string.total_photos,
                                category.getTotalPhotos()
                            ),
                            style = MaterialTheme.typography.labelSmall
                        )
                        category.getMostExpensivePhoto()?.let { mostExpensivePhoto ->
                            Text(
                                text = stringResource(
                                    id = R.string.most_expensive_photo,
                                    stringResource(id = mostExpensivePhoto.title)
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                        category.getMostPopularPhoto()?.let { mostPopularPhoto ->
                            Text(
                                text = stringResource(
                                    id = R.string.most_popular_photo,
                                    stringResource(id = mostPopularPhoto.title)
                                ),
                                style = MaterialTheme.typography.labelSmall
                            )
                        }
                    }

                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCategoryScreen() {
    KunstAppTheme {
        CategoryScreen {

        }
    }
}