package br.com.las.petshop.features.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.petshop.common.cardElevation
import br.com.las.petshop.common.minPadding
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.features.R
import br.com.las.petshop.features.components.header.ScreenHeader
import br.com.las.petshop.features.components.loading.Loading
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

@Composable
fun MainScreenView(viewModel: MainScreenViewModel) {
    when (val screenState = viewModel.screenState.collectAsState().value) {
        is MainScreenViewModel.FetchState.Idle -> Loading()
        is MainScreenViewModel.FetchState.Success -> {
            if (screenState.items.isNotEmpty()) {
                ListItems(itemList = screenState.items,
                    cartClickListener = { viewModel.onCartClicked() })
                    {
                        viewModel.onItemClicked(it)
                    }

            }
        }
    }
}

@Composable
fun ListItems(
    itemList: List<Item>,
    cartClickListener: () -> Unit,
    itemClickListener: (Item) -> Unit
) {
    Column {
        ScreenHeader(title = "Lista de Itens") {
            cartClickListener()
        }
        Row {
            LazyVerticalGrid(
                columns = GridCells.Fixed(integerResource(id = R.integer.grid_number_items)),
                contentPadding = PaddingValues(minPadding)
            ) {
                items(itemList) { item ->
                    ItemCard(item.description, item.imageUrl) { itemClickListener(item) }
                }
            }

        }
    }

}

@Composable
fun ItemCard(itemDescription: String, iconUrl: String?, onClickListener: () -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(cardElevation),
        modifier = Modifier
            .padding(minPadding)
            .clickable { onClickListener() }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            val painter = rememberAsyncImagePainter(model = iconUrl)

            AsyncImage(
                model = iconUrl,
                contentDescription = itemDescription,
                modifier = Modifier
                    .padding(10.dp)
                    .aspectRatio(3 / 4f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

            Spacer(modifier = Modifier.height(minPadding))

            Text(
                text = itemDescription,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(60.dp),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun ItemListPreview() {
    ListItems(
        itemList = listOf(
            Item(
                id = 1000,
                description = "Item Test 1",
                amount = "99",
                imageUrl = "",
                quantity = 99,
                weight = "200gr"
            ),
            Item(
                id = 1000,
                description = "Item Test 2",
                amount = "99",
                imageUrl = "",
                quantity = 99,
                weight = "200gr"
            )
        ),
        itemClickListener = { },
        cartClickListener = { }
    )
}

@Preview
@Composable
fun ItemCardPreview() {
    ItemCard(
        itemDescription = "Test",
        iconUrl = "",
        onClickListener = { }
    )
}