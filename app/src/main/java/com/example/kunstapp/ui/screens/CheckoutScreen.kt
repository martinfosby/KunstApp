package com.example.kunstapp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.kunstapp.R
import com.example.kunstapp.data.OrderUiState
import com.example.kunstapp.datasource.DataSource
import com.example.kunstapp.ui.theme.KunstAppTheme


@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
    onPayClicked: () -> Unit = {},
) {
    var showDialog by remember { mutableStateOf(false) }

    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxSize()) {
        item {
            CartCardList(orderUiState = orderUiState)
            Spacer(modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)))
        }
        item {
            PaymentCard(onPayClicked = { showDialog = true })
        }
    }
    if (showDialog) {
        AlertDialogPayment(
            onDismissRequest = {
                showDialog = false
                onPayClicked()
            },
            onConfirmation = {
                showDialog = false
                onPayClicked()
            },
            dialogTitle = R.string.dialog_title,
            dialogText = R.string.dialog_text,
            icon = Icons.Filled.Done
        )
    }
}

@Composable
fun CartCardList(
    modifier: Modifier = Modifier,
    orderUiState: OrderUiState,
) {
    Card {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            orderUiState.shoppingCart.forEach {
                Card(
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = dimensionResource(
                            id = R.dimen.small_elevation
                        )
                    ),
                    border = BorderStroke(
                        width = dimensionResource(id = R.dimen.small_width),
                        MaterialTheme.colorScheme.tertiary
                    ),
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.small_width))
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_small))
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = it.title),
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Text(
                            text = stringResource(
                                id = R.string.calculated_price,
                                it.getTotalPrice()
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = stringResource(
                                id = R.string.artist_name,
                                stringResource(id = it.artist.nameResId)
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = stringResource(
                                id = R.string.size,
                                stringResource(id = it.size.titleResId)
                            ),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.frame_title),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = stringResource(id = it.frame.title),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.frame_width),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = stringResource(id = it.width.title),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_large)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = stringResource(id = R.string.frame_color),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                text = it.frame.color.toArgb().toString(),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }

                    }

                }
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(
                    id = R.string.total_price_with_mva,
                    orderUiState.mvaPrice
                ),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }

}

@Composable
fun PaymentCard(
    modifier: Modifier = Modifier,
    onPayClicked: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.payment_option),
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(text = stringResource(id = R.string.credit_card), style = MaterialTheme.typography.headlineSmall)
            Text(text = stringResource(id = R.string.credit_card_expiration), style = MaterialTheme.typography.headlineSmall)
            Text(text = stringResource(id = R.string.credit_card_security_code), style = MaterialTheme.typography.headlineSmall)
            Button(
                onClick = onPayClicked,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(dimensionResource(id = R.dimen.padding_medium))
            ) {
                Text(text = stringResource(id = R.string.pay))
            }
        }
    }
}

@Composable
fun AlertDialogPayment(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: Int,
    dialogText: Int,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = null)
        },
        title = {
            Text(text = stringResource(id = dialogTitle), textAlign = TextAlign.Center)
        },
        text = {
            Text(text = stringResource(id = dialogText), textAlign = TextAlign.Center)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onConfirmation() },
            ) {
                Text(stringResource(id = R.string.ok))
            }
        }
    )
}

@Preview
@Composable
fun PreviewCheckoutScreen() {
    KunstAppTheme {
        CheckoutScreen(
            orderUiState = OrderUiState(
                shoppingCart = mutableListOf(DataSource.photos[0], DataSource.photos[1]),
            )
        )
    }
}