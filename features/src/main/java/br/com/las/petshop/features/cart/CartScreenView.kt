package br.com.las.petshop.features.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.petshop.common.shortDescription
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.features.R
import br.com.las.petshop.features.components.header.ScreenHeader
import br.com.las.petshop.features.components.loading.Loading
import br.com.las.petshop.features.theme.PurpleGrey80
import coil.compose.AsyncImage

@Composable
fun CartScreenView(viewModel: CartScreenViewModel) {
    when (val screenState = viewModel.screenState.collectAsState().value) {
        is CartScreenViewModel.FetchState.Idle -> Loading()
        is CartScreenViewModel.FetchState.Success -> {
            CartItems(
                cartItems = screenState.itemsOnCart,
                onShareCartList = { viewModel.shareList() })
            {
                viewModel.onDeleteEventClick(it)
            }
        }

    }
}

@Composable
fun CartScreenHeader() {
    ScreenHeader("Seu Carrinho") { }
}

@Composable
fun CartItems(
    cartItems: List<Item>,
    onShareCartList: () -> Unit,
    onDeleteEventClick: (Item) -> Unit
) {
    val context = LocalContext.current
    Column {
        CartScreenHeader()
        if (cartItems.isNotEmpty()) {
            Row {
                LazyColumn(
                    modifier = Modifier.padding(
                        14.dp
                    )
                ) {
                    items(items = cartItems) { item ->
                        CartListItem(item = item) {
                            onDeleteEventClick(it)
                            Toast.makeText(context, "Item deletado com sucesso", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun LazyItemScope.CartListItem(item: Item, removeItem: (Item) -> Unit) {
    CartItemBackground {
        CartArticleIcon(item.imageUrl)
        Row(
            modifier = Modifier
                .background(color = PurpleGrey80)
                .fillParentMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            Column(modifier = Modifier.padding(end = 10.dp)) {
                Text(
                    text = shortDescription(item.description),
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
            Image(
                painterResource(id = R.drawable.ic_delete),
                contentDescription = "Cart button icon",
                modifier = Modifier
                    .clickable {
                        removeItem(item)
                    }
                    .padding(10.dp)
                    .size(32.dp)
            )
        }
    }
}

@Composable
fun CartItemBackground(content: @Composable BoxWithConstraintsScope.() -> Unit) {
    BoxWithConstraints(
        modifier = Modifier
            .height(72.dp)
            .padding(bottom = 10.dp)
            .clip(shape = RoundedCornerShape(12.dp))

    ) {
        content.invoke(this)
    }
}

@Composable
fun CartArticleIcon(iconUrl: String) {
    // val painter = rememberImagePainter(data = iconUrl)
    AsyncImage(
        model = iconUrl,
        contentDescription = "",
        modifier = Modifier
            .background(color = Color.Red)
            //.aspectRatio(1 / 1f),
            .width(50.dp)
            .height(100.dp)
    )
//    Image(
//        painter = painter,
//
//        contentDescription = "",
//        modifier = Modifier
//            .background(color = Color.Red)
//            //.aspectRatio(1 / 1f),
//            .width(50.dp)
//            .height(50.dp),
//        contentScale = ContentScale.Fit,
//        alignment = Alignment.Center
//        painter = painter,
//        contentDescription = "item thumb",
//        contentScale = ContentScale.Crop,
//        modifier = Modifier.fillMaxSize()
//        Modifier
//            .height(maxHeight)
////            .size(24.dp)
//            .width(100.dp)
//            .clip(shape = RoundedCornerShape(20.dp))
//    )
}

@Preview
@Composable
fun CartItemsScreenPreview() {
    CartItems(
        cartItems = listOf(
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
        onShareCartList = { /*TODO*/ },
        onDeleteEventClick = { }
    )
}