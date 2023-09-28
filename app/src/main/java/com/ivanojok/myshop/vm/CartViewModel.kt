package com.ivanojok.myshop.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ivanojok.myshop.data.repository.MainRepository
import com.ivanojok.myshop.data.room.CartModel
import com.ivanojok.myshop.data.room.DBBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CartViewModel(val repository: MainRepository) : ViewModel() {

    suspend fun getCartList(context: Context): List<CartModel> {
        return repository.getCartList(context)
    }

    suspend fun addQuantity(context: Context, id:Int): Int {
        val x = viewModelScope.async(Dispatchers.IO) {
            val dao = DBBuilder().initializeDB(context).createCartDao()
            val product = dao.getProduct(id)
            val quantity = product.quantity
            val new = quantity + 1
            //update value
            val p = CartModel(id, product.product, new)
            dao.updateCartProduct(p)
            new
        }
        return x.await()
    }

    suspend fun removeQuantity(context: Context, id:Int): Int {
        val x = viewModelScope.async(Dispatchers.IO) {
            val dao =  DBBuilder().initializeDB(context).createCartDao()
            val product = dao.getProduct(id)
            val quantity = product.quantity
            val new = quantity - 1
            if (new == 0){
                dao.deleteProduct(product)
            } else {
                val p = CartModel(id, product.product, new)
                dao.updateCartProduct(p)
            }
            new
        }
    return x.await()
    }
}