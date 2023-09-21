package com.ivanojok.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import com.ivanojok.myshop.R
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.model.Rating
import com.ivanojok.myshop.data.room.CartModel
import com.ivanojok.myshop.vm.MainViewModel
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {

    val viewModel:MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //val productImage = intent.getStringExtra("productImage")
        //val productId = intent.getStringExtra("productId")
        //val productName = intent.getStringExtra("productName")
       // val productPrice = intent.getStringExtra("productPrice")
        //val productDescription = intent.getStringExtra("productDescription")

        val productId = intent.getStringExtra("productId")
        val productName = intent.getStringExtra("productName")
        val productPrice =  intent.getStringExtra("productPrice")
        val productDescription =  intent.getStringExtra("productDescription")
        val productCategory = intent.getStringExtra("productCategory")
        val productImage = intent.getStringExtra("productImage")
        val ratingRate =intent.getStringExtra("ratingRate")
        val ratingCount = intent.getStringExtra("ratingCount")

        Picasso.get().load(productImage).into(findViewById<ImageView>(R.id.p_image))
        findViewById<TextView>(R.id.p_name).text = productName
        findViewById<TextView>(R.id.p_price).text = productPrice
        findViewById<TextView>(R.id.p_description).text = productDescription

        findViewById<Button>(R.id.add_to_cart).setOnClickListener {
            try {
                val product = ProductResponse(productId?.toInt(),
                        productName,
                        productPrice?.toDouble(),
                        productDescription,
                        productCategory, productImage, Rating(ratingRate?.toDouble(), ratingCount?.toInt()))
                val item = CartModel(null, product, 1)
                viewModel.insertCartItem(this, item)
            } catch (t: Throwable) {
                Toast.makeText(this, "$t", Toast.LENGTH_SHORT).show()

            }

        }

    }
}