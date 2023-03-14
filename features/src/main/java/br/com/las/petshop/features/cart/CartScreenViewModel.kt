package br.com.las.petshop.features.cart

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.repositories.local.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _screenState: MutableStateFlow<FetchState> = MutableStateFlow(FetchState.Idle)

    lateinit var screenEventsHandler: CartScreenEvents
    val screenState: StateFlow<FetchState> = _screenState

    init {
        viewModelScope.launch {
            _screenState.value = FetchState.Success(cartRepository.getCartItems())
        }
    }

    fun onDeleteEventClick(item: Item) {
        viewModelScope.launch {
            _screenState.update {
                cartRepository.delete(item)
                FetchState.Success(cartRepository.getCartItems())
            }
        }
    }

    fun shareList() {

    }

    sealed class FetchState {
        object Idle : FetchState()
        data class Success(val itemsOnCart: List<Item>) : FetchState()
    }

}