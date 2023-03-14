package br.com.las.petshop.data.cached.dao

import androidx.room.Dao

import androidx.room.Query
import br.com.las.petshop.data.cached.base.BaseDao
import br.com.las.petshop.data.data.Item

@Dao
sealed interface ItemDao: BaseDao<Item> {
    @Query("SELECT * FROM Item")
    fun getAll(): List<Item>

}