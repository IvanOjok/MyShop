package com.ivanojok.myshop.data.model


data class ProductResponse(
    var id:Int?,
    var title:String?,
    var price:Double?,
    var description:String?,
    var category:String?,
    var image:String?,
    var rating: Rating
)

data class Rating(
    var rate:Double?,
    var count:Int?
)