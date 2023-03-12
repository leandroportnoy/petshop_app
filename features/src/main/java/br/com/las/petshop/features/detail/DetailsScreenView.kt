package br.com.las.petshop.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.features.R.*
import br.com.las.petshop.features.components.loading.Loading
import br.com.las.petshop.features.theme.Grey_900
import coil.compose.rememberImagePainter

@Composable
internal fun DetailsScreenView(viewModel: DetailsScreenViewModel) {
    when (val screenState = viewModel.screenState.collectAsState().value) {
        is DetailsScreenViewModel.ScreenState.Loading -> Loading()
        is DetailsScreenViewModel.ScreenState.Fetched -> ShowDetails(
            screenState.item
        )
    }
}

@Composable
private fun ShowDetails(
    item: Item
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            val painter = rememberImagePainter(data = item.imageUrl)

            Image(
                painter = painter,
                contentDescription = item.description,
                modifier = Modifier
                    .padding(50.dp)
                    .aspectRatio(3 / 3f),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = item.description,
                style = MaterialTheme.typography.headlineSmall,
                color = Grey_900,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp, 5.dp, 5.dp, 5.dp),
            textAlign = TextAlign.Start,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = " Peso:",
                color = Grey_900,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp),
            )
            Text(
                text = item.quantity.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Grey_900,
                modifier = Modifier
                    .padding(0.dp, 5.dp, 5.dp, 5.dp),
                textAlign = TextAlign.Start,
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = " Valor:",
                color = Grey_900,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp),
            )
            Text(
                text = item.amount,
                style = MaterialTheme.typography.bodyLarge,
                color = Grey_900,
                modifier = Modifier
                    .padding(0.dp, 5.dp, 5.dp, 5.dp),
                textAlign = TextAlign.Start,
            )

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = " Quantidade:",
                color = Grey_900,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(5.dp, 5.dp, 5.dp, 5.dp),
            )
            Text(
                text = item.quantity.toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Grey_900,
                modifier = Modifier
                    .padding(0.dp, 5.dp, 5.dp, 5.dp),
                textAlign = TextAlign.Start,
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = MaterialTheme.colorScheme.primary),
                onClick = {
                    // handle button click
                }
            )
            {
                Image(
                    painterResource(id = drawable.ic_cart),
                    contentDescription ="Cart button icon",
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = "Adicionar",
                    Modifier.padding(start = 10.dp)
                )
            }
        }
    }

}


@Preview
@Composable
fun LoadingPreview() {
//    Loading()
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DetailsPreview() {
    ShowDetails(
        item = Item(
            id = 1000,
            description = "Item Test",
            amount = "99",
            imageUrl = "",
            quantity = 99,
            weight = "200gr"
        )
    )

}