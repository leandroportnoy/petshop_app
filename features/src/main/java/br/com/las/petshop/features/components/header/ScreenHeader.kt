package br.com.las.petshop.features.components.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.petshop.features.R

@Composable
fun ScreenHeader(
    title: String,
    eventClick: () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // left side: label
        Column {
            Text(text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(start = 20.dp, top = 30.dp, bottom = 15.dp),
                color = Color.White
            )
        }
        // right side: user icon

        Image(
            painterResource(id = R.drawable.ic_cart),
            contentDescription ="Cart button icon",
            modifier = Modifier
                .clickable {
                    eventClick()
                }
                .padding(end = 20.dp, top = 30.dp, bottom = 15.dp)
                .size(24.dp),
        )
    }
}

@Preview
@Composable
fun HeaderPreview() {
    ScreenHeader(title = "Lista de Itens") { }
}