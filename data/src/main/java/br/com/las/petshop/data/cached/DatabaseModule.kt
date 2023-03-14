package br.com.las.petshop.data.cached

import android.content.Context
import androidx.room.Room
import br.com.las.petshop.data.cached.dao.ItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideChannelDao(appDatabase: PetShopDatabase): ItemDao {
        return appDatabase.itemDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): PetShopDatabase {
        return Room.databaseBuilder(
            appContext,
            PetShopDatabase::class.java,
            "PetShopDatabase"
        ).build()
    }
}
//
//@Module
//@InstallIn(SingletonComponent::class)
//abstract class CartProvider {
//    @Singleton
//    @Binds
//    internal abstract fun getCartLocalRepository(impl: CartRepositoryImpl): CartRepository
//}