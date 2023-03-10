package br.com.las.petshop.data.repositories

import androidx.annotation.WorkerThread
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.services.RestApi
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

internal class PetShopRepositoryImpl @Inject constructor(private val restApi: RestApi): PetShopRepository {
    @WorkerThread
    override suspend fun getItems(): List<Item> =
        withContext(Dispatchers.IO) {
            restApi.getListItems().list.map {
                Item(it.id, it.description, it.weight, it.quantity, it.amount, it.imageUrl)
            }
        }

    @WorkerThread
    override suspend fun getItem(itemId: Long): Item =
        restApi.getListItems().list.single { item ->
            item.id == itemId
        }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class PetShopProvider {
    @Singleton
    @Binds
    internal abstract fun getTVMazeRepository(impl: PetShopRepositoryImpl): PetShopRepository
}
