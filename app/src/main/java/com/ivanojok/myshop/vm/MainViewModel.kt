package com.ivanojok.myshop.vm

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.repository.MainRepository
import com.ivanojok.myshop.data.repository.ProductListResponse
import com.ivanojok.myshop.data.room.CartModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(val mainRepository: MainRepository): ViewModel() {

    val _productListData = MutableStateFlow<CompleteResponse>(CompleteResponse.Loading)
    val productListData = _productListData.asLiveData()

    suspend fun getRemoteItems() {
       mainRepository.getRemoteProducts().collect {
           when(it) {
               is ProductListResponse.Loading -> _productListData.value  = CompleteResponse.Loading
               is ProductListResponse.Success -> _productListData.value = CompleteResponse.Success(it.data)
               is ProductListResponse.Failure -> _productListData.value = CompleteResponse.Failure(it.error)
           }
       }
    }

    fun insertCartItem(context:Context, item: CartModel) {
        mainRepository.insertCartItem(context, item)
    }

    sealed class CompleteResponse {
        object Loading : CompleteResponse()
        class Success(val data: List<ProductResponse>): CompleteResponse()

        class Failure(val error:String): CompleteResponse()
    }



}