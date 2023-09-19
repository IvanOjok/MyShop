package com.ivanojok.myshop.data.retofit

import com.ivanojok.myshop.data.model.ProductResponse
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): List<ProductResponse>
}