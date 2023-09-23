package com.ivanojok.myshop.data.retofit

import com.ivanojok.myshop.data.model.ProductResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ProductService {

    @GET("/products")
    suspend fun getProducts(): List<ProductResponse>

}