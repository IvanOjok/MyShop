package com.ivanojok.myshop.data.repository

import com.ivanojok.myshop.data.model.ProductResponse

sealed class ProductListResponse {
    object Loading: ProductListResponse()

    class Success(var data:List<ProductResponse>): ProductListResponse()

    class Failure(var error:String): ProductListResponse()
}