package com.ivanojok.myshop.retofit

import com.ivanojok.myshop.model.ProductResponse
import retrofit2.http.GET

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): List<ProductResponse>
}