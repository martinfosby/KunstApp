package com.example.kunstapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun CategoryScreen(
    modifier: Modifier = Modifier,
    onCategoryClicked: (Category) -> Unit,
) {
    LazyColumn {
        items(Category.entries) { category ->
            CategoryCard(category = category, onCategoryClicked = onCategoryClicked)
        }
    }
}


@Composable
fun CategoryCard(
    modifier: Modifier = Modifier,
    category: Category,
    onCategoryClicked: (Category) -> Unit,
) {
    Card(
        modifier = Modifier
            .clickable { onCategoryClicked(category) }
            .padding(
                dimensionResource(id = R.dimen.padding_small)

            )
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = stringResource(id = category.desc),
                modifier = Modifier.size(dimensionResource(id = R.dimen.photo_extra_small))
            )
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(id = category.desc),
                    style = MaterialTheme.typography.labelLarge,
                )
                Text(
                    text = stringResource(
                        id = R.string.total_photos,
                        category.getTotalPhotos()
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                category.getMostExpensivePhoto()?.let { mostExpensivePhoto ->
                    Text(
                        text = stringResource(
                            id = R.string.most_expensive_photo,
                            stringResource(id = mostExpensivePhoto.title)
                        ),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                category.getMostPopularPhoto()?.let { mostPopularPhoto ->
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
fun PreviewCategoryScreen() {
    KunstAppTheme {
        CategoryScreen {

        }
    }
}