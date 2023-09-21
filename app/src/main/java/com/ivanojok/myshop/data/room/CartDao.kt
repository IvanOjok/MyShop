package com.ivanojok.myshop.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertProductToCart(product:CartModel)

    @Query("Select * from cartmodel")
    fun selectAllProducts(): List<CartModel>

    @Update()
    fun updateCartProduct(product: CartModel)

}