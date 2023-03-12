package br.com.las.petshop.features.cart

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.repositories.PetShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private  val petShopRepository: PetShopRepository
): ViewModel() {

    lateinit var screenEventsHandler : CartScreenEvents

    private val itemsOnCartList : MutableList<Item> = mutableListOf()
    private val _screenState : MutableStateFlow<FetchState> = MutableStateFlow(
        FetchState.Idle)

    val screenState : StateFlow<FetchState> = _screenState

    init {
        viewModelScope.launch {
            itemsOnCartList.addAll(petShopRepository.getItems())
            _screenState.value = FetchState.Success(itemsOnCartList)
        }
    }

    fun onDeleteEventClick(item: Item) {

    }

    fun shareList() {

    }

    sealed class FetchState {
        object Idle : FetchState()
        data class Success(val itemsOnCart : List<Item>) : FetchState()
//        data class Delete(val item : Item) : FetchState()
    }

}