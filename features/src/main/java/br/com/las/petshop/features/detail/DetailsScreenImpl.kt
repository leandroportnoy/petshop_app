package br.com.las.petshop.features.detail

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

internal class DetailsScreenImpl @Inject constructor(): DetailsScreen {

    override val plainDestination = "show_details_screen"
    override val destination = plainDestination + "/{" + DetailsScreen.DETAIL_ID_ARGS + "}"

    override fun onCreateNavGraph(
        navGraphBuilder: NavGraphBuilder,
        detailsScreenEvents: DetailsScreenEvents
    ) {
        navGraphBuilder.composable(
            destination,
            listOf(navArgument(DetailsScreen.DETAIL_ID_ARGS) { type = NavType.LongType })
        ) {
            val viewModel = hiltViewModel<DetailsScreenViewModel>()
            viewModel.screenEventsHandler = detailsScreenEvents
            DetailsScreenView(viewModel = viewModel)
        }
    }
}

@Module
@InstallIn(ActivityComponent::class)
internal abstract class DetailsScreenProvider {

    @ActivityScoped
    @Binds
    abstract fun getDetailsScreenFeature(api: DetailsScreenImpl): DetailsScreen
}