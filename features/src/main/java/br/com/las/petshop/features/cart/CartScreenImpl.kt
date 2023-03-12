package br.com.las.petshop.features.cart

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

class CartScreenImpl @Inject constructor(): CartScreen {

    override val plainDestination: String = "cart_screen"
    override val destination: String = plainDestination

    override fun onCreateNavGraph(
        navGraphBuilder: NavGraphBuilder,
        featuredEvents: CartScreenEvents
    ) {
        navGraphBuilder.composable(destination) {
            val cartScreenViewModel = hiltViewModel<CartScreenViewModel>()
            cartScreenViewModel.screenEventsHandler = featuredEvents
            CartScreenView(viewModel = cartScreenViewModel)
        }
    }

}

@Module
@InstallIn(ActivityComponent::class)
abstract class CartScreenProvider {
    @ActivityScoped
    @Binds
    abstract fun getCartScreenScene(api: CartScreenImpl): CartScreen
}
