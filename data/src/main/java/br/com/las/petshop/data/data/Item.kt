package br.com.las.petshop.data.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "weight") val weight: String,
    @ColumnInfo(name = "quantity") val quantity: Long,
    @ColumnInfo(name = "amount") val amount: String,
    @ColumnInfo(name = "image_url") val imageUrl: String
)
