package br.com.las.petshop.features.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.repositories.local.CartRepository
import br.com.las.petshop.data.repositories.remote.PetShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class DetailsScreenViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val petShopRepository: PetShopRepository,
    private val cartRepository: CartRepository
) : ViewModel() {

    lateinit var screenEventsHandler: DetailsScreenEvents

    private val _screenState: MutableStateFlow<ScreenState> = MutableStateFlow(ScreenState.Loading)
    val screenState: StateFlow<ScreenState> = _screenState

    init {
        val itemId = savedStateHandle.get<Long>(DetailsScreen.DETAIL_ID_ARGS)
        if (itemId != null) {
            viewModelScope.launch {
                val itemSelected = petShopRepository.getItem(itemId)
                _screenState.value = ScreenState.Fetched(itemSelected)
            }
        }
    }

    fun insertItemClicked(item: Item) {
        viewModelScope.launch {
            cartRepository.insert(item)
            screenEventsHandler.backToMainScreen()
        }
    }

    sealed class ScreenState {
        object Loading : ScreenState()
        data class Fetched(val item: Item) : ScreenState()
    }
}