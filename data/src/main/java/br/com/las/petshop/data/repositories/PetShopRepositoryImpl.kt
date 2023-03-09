package br.com.las.petshop.data.repositories

import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.services.RestApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class PetShopRepositoryImpl @Inject constructor(private val restApi: RestApi): PetShopRepository {

    override suspend fun getItems(): List<Item> =
        withContext(Dispatchers.IO) {
            restApi.getListItems().map {
                Item(it.id, it.description, it.weight, it.quantity, it.amount, it.imageUrl)
            }
        }


    override suspend fun getItem(): Item {
        TODO("Not yet implemented")
    }
}