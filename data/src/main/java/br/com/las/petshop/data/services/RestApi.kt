package br.com.las.petshop.data.services

import br.com.las.petshop.data.data.Item

interface RestApi {
    suspend fun getListItems(): List<Item>
}