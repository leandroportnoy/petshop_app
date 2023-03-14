package br.com.las.petshop.data.repositories.local

import androidx.annotation.WorkerThread
import br.com.las.petshop.data.data.Item

sealed interface CartRepository {

    @WorkerThread
    suspend fun getCartItems(): List<Item>

    @WorkerThread
    suspend fun insert(item: Item)

    @WorkerThread
    suspend fun delete(item: Item)

}