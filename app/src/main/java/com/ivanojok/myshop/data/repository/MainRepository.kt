package com.ivanojok.myshop.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.retofit.RetrofitInstance
import com.ivanojok.myshop.data.room.CartModel
import com.ivanojok.myshop.data.room.DBBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.Flow
import java.util.InvalidPropertiesFormatException

class MainRepository(val retrofit:RetrofitInstance, var room:DBBuilder) {

    suspend fun getRemoteProducts(): Flow<ProductListResponse>  = flow {
        emit(ProductListResponse.Loading)
        try {
            var products: List<ProductResponse> ?= null
            val x = CoroutineScope(Dispatchers.IO).async {
                Log.d("products1", "$products")
                val service = retrofit.createProductService()
                products = service.getProducts()
                Log.d("products2", "$products")
                products
            }
            Log.d("products3", "$products")
            products = x.await()

            if (products != null) {
                emit(ProductListResponse.Success(products!!))
            } else {
                throw InvalidPropertiesFormatException("Products is null")
            }

        } catch (error: Throwable) {
            emit(ProductListResponse.Failure("${error.message}"))
        }

    }

    fun insertCartItem(context: Context, item: CartModel) {
        CoroutineScope(Dispatchers.IO).launch {
            room.initializeDB(context).createCartDao().insertProductToCart(item)
        }
    }

    suspend fun getCartList(context: Context): List<CartModel> {
        val x = CoroutineScope(Dispatchers.IO).async {
            val list = DBBuilder().initializeDB(context).createCartDao().selectAllProducts()
            list
        }
        return x.await()
    }

    suspend fun addQuantity(context: Context, id:Int): Int {
        val x = CoroutineScope(Dispatchers.IO).async {
            val product = getProduct(context, id)
            val quantity = product.quantity
            val new = quantity + 1
            //update value
            val p = CartModel(id, product.product, new)
            updateProduct(context, p)
            new
        }
        return x.await()
    }

    suspend fun getProduct(context:Context, id:Int): CartModel {
        val job = CoroutineScope(Dispatchers.IO).async {
            val dao = room.initializeDB(context).createCartDao()
            val product = dao.getProduct(id)
            product
        }
        return job.await()
    }

    suspend fun updateProduct(context:Context, product:CartModel) {
        room.initializeDB(context).createCartDao().updateCartProduct(product)
    }

}