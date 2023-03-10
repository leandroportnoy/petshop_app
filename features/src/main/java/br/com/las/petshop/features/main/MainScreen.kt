package br.com.las.petshop.features.main

import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.SceneEvents

interface MainScreen : SceneContract<MainScreenEvents>

interface MainScreenEvents: SceneEvents {

    fun onItemSelected(itemId : Long)
}