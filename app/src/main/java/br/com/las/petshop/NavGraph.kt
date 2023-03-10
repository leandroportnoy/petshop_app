package br.com.las.petshop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.detail.DetailsScreen
import br.com.las.petshop.features.detail.DetailsScreenEvents
import br.com.las.petshop.features.main.MainScreen
import br.com.las.petshop.features.main.MainScreenEvents

@Composable
fun NavGraph(
    startDestination: SceneContract<*>,
    mainScreen: MainScreen,
    detailsScreen: DetailsScreen
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination.destination) {

        //main screen
        mainScreen.onCreateNavGraph(this, object : MainScreenEvents {
            override fun onItemSelected(itemId: Long) {
                navController.navigate(detailsScreen.plainDestination + "/" + itemId)
            }
        })
        //show details
        detailsScreen.onCreateNavGraph(this, object : DetailsScreenEvents {
            override fun onClick(itemId: Long) {
                //some event in the future
            }
        })

    }
}