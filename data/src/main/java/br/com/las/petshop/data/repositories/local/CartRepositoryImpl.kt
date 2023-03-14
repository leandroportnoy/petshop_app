package br.com.las.petshop.data.repositories.local

import android.util.Log
import br.com.las.petshop.data.cached.dao.ItemDao
import br.com.las.petshop.data.data.Item
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

internal class CartRepositoryImpl @Inject constructor(private val itemDao: ItemDao): CartRepository {

    companion object {
        const val TAG = "CartRepositoryImpl"
    }

    override suspend fun getCartItems(): List<Item> = withContext(Dispatchers.IO) { itemDao.getAll() }

    override suspend fun insert(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.insert(item)
            Log.d(TAG, "new item added")
        }
    }

    override suspend fun delete(item: Item) {
        withContext(Dispatchers.IO) {
            itemDao.delete(item)
            Log.d(TAG, "selected item removed")
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CartProvider {
    @Singleton
    @Binds
    internal abstract fun getCartLocalRepository(impl: CartRepositoryImpl): CartRepository
}
