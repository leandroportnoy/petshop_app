package br.com.las.petshop.features.cart

import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.SceneEvents

interface CartScreen: SceneContract<CartScreenEvents>

interface CartScreenEvents: SceneEvents {
    //fun onDelete(itemId: Long)
    fun onShareClicked()
}
