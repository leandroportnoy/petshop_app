package br.com.las.petshop.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import br.com.las.petshop.NavGraph
import br.com.las.petshop.features.main.MainScreen
import br.com.las.petshop.ui.theme.PetShopTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    @Inject
    lateinit var mainScreen: MainScreen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PetShopTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BuildNavGraph()
                }
            }
        }
    }

    @Composable
    fun BuildNavGraph() {
        val startingScreen = mainScreen
        NavGraph(
            startDestination = startingScreen,
//        splashScreen = splashScreen,
            mainScreen = mainScreen,
//        showDetailsScreen = showDetailsScreen
        )
    }
}


