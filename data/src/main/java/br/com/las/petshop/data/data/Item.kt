package br.com.las.petshop.data.data

data class Item(
    val id: Long,
    val description: String,
    val weight: String,
    val quantity: Long,
    val amount: String,
    val imageUrl: String
)
