package br.com.las.petshop.data

import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.data.ProductList
import br.com.las.petshop.data.services.RestApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetItemsTest {

    @Test
    fun testGetItemsEmptyList() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getListItems() } } doReturn ProductList(listOf())
        }
        val result = restApi.getListItems()
        assert(result.list.isEmpty())
    }

    @Test
    fun testGetItemsWithItem() = runBlocking {
        val restApi = mock<RestApi> {
            on { runBlocking { getListItems() } } doReturn ProductList(
                listOf(
                    Item(
                        1,
                        "item",
                        "99 kg",
                        9,
                        "9",
                        ""
                    )
                )
            )
        }
        val result = restApi.getListItems()
        assert(result.list.isNotEmpty())
    }

}