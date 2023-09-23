package com.ivanojok.myshop.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProductToCart(product:CartModel)

    @Query("Select * from cartmodel")
    fun selectAllProducts(): List<CartModel>

    @Query("Select * from cartmodel where :id")
    fun getProduct(id:Int): CartModel

    @Update()
    fun updateCartProduct(product: CartModel)

    @Delete()
    fun deleteProduct(product:CartModel)

}