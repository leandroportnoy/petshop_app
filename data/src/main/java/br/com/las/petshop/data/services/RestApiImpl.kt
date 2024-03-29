package br.com.las.petshop.data.services

import android.content.Context
import android.util.Log
import br.com.las.petshop.common.idToString
import br.com.las.petshop.data.R
import br.com.las.petshop.data.data.Item
import br.com.las.petshop.data.data.ProductList
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.json.JSONException
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.suspendCoroutine

internal class RestApiImpl @Inject constructor(@ApplicationContext val appContext: Context) : RestApi {

    companion object {
        const val TAG = "RestApiImpl"

        /** URL to retrieve a list of items */
        const val GET_LIST_ITEMS = "https://run.mocky.io/v3/aa97786c-0a45-43dd-bffb-f0a6c541a449"
    }

    override suspend fun getListItems(): ProductList =
        suspendCoroutine { continuation ->
            Volley.newRequestQueue(appContext)
                .add(StringRequest(Request.Method.GET, GET_LIST_ITEMS, { response ->
                    try {
                        val objType = object : TypeToken<ProductList>() {}.type
                        continuation.resumeWith(Result.success(Gson().fromJson(response, objType)))
                    } catch (exception: JSONException) {
                        Log.e(TAG, R.string.lbl_error_while_retrieve_data.idToString(appContext), exception)
//                        continuation.resumeWith(Result.success(emptyList()))
                    }
                }) { volleyError ->
                    Log.e(TAG, R.string.lbl_error_while_setting_volley.idToString(appContext).plus(volleyError))
//                    continuation.resumeWith(Result.success(emptyList()))
                })
        }

}

@Module
@InstallIn(SingletonComponent::class)
abstract class RestAPIProvider {
    @Singleton
    @Binds
    internal abstract fun getPetShopRepository(impl: RestApiImpl): RestApi
}