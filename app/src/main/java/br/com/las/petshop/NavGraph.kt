package br.com.las.petshop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.cart.CartScreen
import br.com.las.petshop.features.cart.CartScreenEvents
import br.com.las.petshop.features.detail.DetailsScreen
import br.com.las.petshop.features.detail.DetailsScreenEvents
import br.com.las.petshop.features.main.MainScreen
import br.com.las.petshop.features.main.MainScreenEvents

@Composable
fun NavGraph(
    startDestination: SceneContract<*>,
    mainScreen: MainScreen,
    detailsScreen: DetailsScreen,
    cartScreen: CartScreen
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination.destination) {

        //main screen
        mainScreen.onCreateNavGraph(this, object : MainScreenEvents {
            override fun onItemSelected(itemId: Long) {
                navController.navigate(detailsScreen.plainDestination + "/" + itemId)
            }

            override fun onCartClicked() {
                navController.navigate(cartScreen.plainDestination)
            }
        })
        //show details
        detailsScreen.onCreateNavGraph(this, object : DetailsScreenEvents {
            override fun backToMainScreen() {
                //some event in the future
                navController.navigate(mainScreen.plainDestination)
            }
        })

        cartScreen.onCreateNavGraph(this, object : CartScreenEvents {
            override fun onShareClicked() {

            }
        })

    }
}