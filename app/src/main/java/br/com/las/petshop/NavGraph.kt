package br.com.las.petshop

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.main.MainScreen
import br.com.las.petshop.features.main.MainScreenEvents

@Composable
fun NavGraph(
    startDestination: SceneContract<*>,
    mainScreen: MainScreen,
    //showDetailsScreen: DetailsScreen
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination.destination) {

        //main screen
        mainScreen.onCreateNavGraph(this, object : MainScreenEvents {
            override fun onItemSelected(itemId: Long) {
                //navController.navigate(showDetailsScreen.plainDestination + "/" + showId)
            }
        })
        //show details
//        showDetailsScreen.onCreateNavGraph(this, object : DetailsScreenEvents {
//            override fun onEpisodeSelected(episodeId: Long) {
//
//            }
//        })

    }
}