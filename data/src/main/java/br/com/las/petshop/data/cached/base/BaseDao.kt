package br.com.las.petshop.data.cached.base

import androidx.room.*
import androidx.room.OnConflictStrategy.Companion.REPLACE

@Dao
interface BaseDao<T> {

    @Insert(onConflict = REPLACE)
    fun insert(entity: T)

    @Delete
    fun delete(entity: T)
}