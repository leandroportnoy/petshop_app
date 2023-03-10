package br.com.las.petshop.data.repositories

import androidx.annotation.WorkerThread
import br.com.las.petshop.data.data.Item


sealed interface PetShopRepository {
    @WorkerThread
    suspend fun getItems(): List<Item>
    @WorkerThread
    suspend fun getItem(itemId: Long): Item
}