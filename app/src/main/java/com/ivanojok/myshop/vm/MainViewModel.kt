package com.ivanojok.myshop.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.retofit.RetrofitInstance
import com.ivanojok.myshop.data.room.CartModel
import com.ivanojok.myshop.data.room.DBBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    suspend fun getRemoteItems(): List<ProductResponse>? {
        var products: List<ProductResponse> ?= null
        val x = viewModelScope.async(Dispatchers.IO) {
            Log.d("products1", "$products")
            val service = RetrofitInstance().createProductService()
            products = service.getProducts()
            Log.d("products2", "$products")
            products
        }
        Log.d("products3", "$products")
        products = x.await()
        return products
    }

    fun insertCartItem(context:Context, item: CartModel) {
        viewModelScope.launch(Dispatchers.IO) {
            DBBuilder().initializeDB(context).createCartDao().insertProductToCart(item)
        }
    }


}