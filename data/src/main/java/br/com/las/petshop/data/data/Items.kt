package br.com.las.petshop.data.data

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("productList")
    val list: List<Item>
)
