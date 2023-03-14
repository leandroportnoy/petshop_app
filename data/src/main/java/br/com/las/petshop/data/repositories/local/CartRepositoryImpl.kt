package br.com.las.petshop.data.repositories.local

import br.com.las.petshop.data.cached.dao.ItemDao
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.repositories.remote.PetShopRepository
import br.com.las.petshop.data.repositories.remote.PetShopRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

internal class CartRepositoryImpl @Inject constructor(private val itemDao: ItemDao): CartRepository {

    override suspend fun getCartItems(): List<Item> = itemDao.getAll()

    override suspend fun insert(item: Item) {
        itemDao.insert(item)
    }

    override suspend fun delete(item: Item) {
        itemDao.delete(item)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CartProvider {
    @Singleton
    @Binds
    internal abstract fun getCartLocalRepository(impl: CartRepositoryImpl): CartRepository
}
