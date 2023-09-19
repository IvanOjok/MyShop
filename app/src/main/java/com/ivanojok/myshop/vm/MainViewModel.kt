package com.ivanojok.myshop.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.retofit.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainViewModel : ViewModel() {

    fun getProducts(): Flow<List<ProductResponse>?> = flow {
        try {
            val service = RetrofitInstance().createProductService()
            val products = service.getProducts()
            emit(products)
        } catch (t: Throwable) {
            Log.d("Error", "$t")
            emit(null)
        }
    }
}