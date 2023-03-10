package br.com.las.petshop.features.detail

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.progressSemantics
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import br.com.las.petshop.common.minPadding
import br.com.las.petshop.data.data.Item
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
private fun Loading(
    size: Dp = 32.dp,
    sweepAngle: Float = 90f,
    color: Color = MaterialTheme.colorScheme.primary,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
) {
    val transition = rememberInfiniteTransition()

    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        )
    )

    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    Box {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary,)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Canvas(
                Modifier
                    .progressSemantics()
                    .size(size)
                    .padding(strokeWidth / 2)
            ) {

                drawCircle(Color.LightGray, style = stroke)

                drawArc(
                    color,
                    startAngle = currentArcStartAngle.toFloat() - 90,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    style = stroke
                )
            }
        }
    }

}

@Composable
private fun ShowDetails(
    item: Item
) {
    val painter = rememberImagePainter(data = item.imageUrl)

    Box {
        Column(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primary,)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(
                modifier = Modifier
                    .padding(50.dp)
            ) {
                Image(
                    painter = painter,
                    contentDescription = item.description,
                    modifier = Modifier
                        .aspectRatio(3 / 3f),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                )
                Divider(modifier = Modifier.height(minPadding))
            }

            Text(
                text = item.description,
                style = MaterialTheme.typography.headlineSmall,
                color = Grey_900,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.CenterHorizontally)
                    .padding(10.dp),
                textAlign = TextAlign.Center,
            )


        }
    }
}


@Preview
@Composable
fun LoadingPreview() {
    Loading()
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
//    Summary(
//        showSummary = "Test",
//        genres = listOf("Drama", "Science-Fiction", "Thriller"),
//        rating = 6.8f,
//        episodes = null
//    )
}