package br.com.las.petshop.features.detail

import br.com.las.petshop.features.SceneContract
import br.com.las.petshop.features.SceneEvents


sealed interface DetailsScreen: SceneContract<DetailsScreenEvents> {

    companion object {
        internal const val DETAIL_ID_ARGS: String = "item_id"
    }
}

interface DetailsScreenEvents: SceneEvents {
    fun onClick(itemId: Long)
}