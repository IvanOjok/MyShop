package com.ivanojok.myshop.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.ivanojok.myshop.data.model.ProductResponse

class Converter {
    @TypeConverter
    fun convertProductObjectToString(item:ProductResponse): String {
       val x =  Gson().toJson(item)
        return x
    }

    @TypeConverter
    fun convertStringToProductObject(item:String): ProductResponse {
        val y = Gson().fromJson(item, ProductResponse::class.java)
        return y
    }
}