package br.com.las.petshop.data.services

import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.data.ProductList

sealed interface RestApi {
    suspend fun getListItems(): ProductList
}