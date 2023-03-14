package br.com.las.petshop.features.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.repositories.remote.PetShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val savedStateHandle: SavedStateHandle,
    private  val petShopRepository: PetShopRepository
): ViewModel() {

    lateinit var screenEventsHandler : MainScreenEvents

    private val currentItemList : MutableList<Item> = mutableListOf()
    private val _screenState : MutableStateFlow<FetchState> = MutableStateFlow(FetchState.Idle)

    val screenState : StateFlow<FetchState> = _screenState

    init {
        viewModelScope.launch {
            currentItemList.addAll(petShopRepository.getItems())
            _screenState.value = FetchState.Success(currentItemList)
        }
    }

    fun onItemClicked(item: Item) {
        screenEventsHandler.onItemSelected(item.id)
    }

    fun onCartClicked() {
        screenEventsHandler.onCartClicked()
        print("screen cart called ------------------------------------------- ")
    }

    sealed class FetchState {
        object Idle : FetchState()
        data class Success(val items : List<Item>) : FetchState()
    }

}