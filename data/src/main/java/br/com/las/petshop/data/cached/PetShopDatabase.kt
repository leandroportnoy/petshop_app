package br.com.las.petshop.data.cached

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.las.petshop.data.cached.dao.ItemDao
import br.com.las.petshop.data.data.Item

@Database(
    entities = [
        Item::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PetShopDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemDao

    companion object {
        const val HORUS_DATABASE = "PetShopDB"
    }
}