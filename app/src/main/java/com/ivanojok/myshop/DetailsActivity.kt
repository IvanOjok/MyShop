package com.ivanojok.myshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val productImage = intent.getStringExtra("productImage")
        val productName = intent.getStringExtra("productName")
        val productPrice = intent.getStringExtra("productPrice")
        val productDescription = intent.getStringExtra("productDescription")

        Picasso.get().load(productImage).into(findViewById<ImageView>(R.id.p_image))
        findViewById<TextView>(R.id.p_name).text = productName
        findViewById<TextView>(R.id.p_price).text = productPrice
        findViewById<TextView>(R.id.p_description).text = productDescription

        findViewById<Button>(R.id.add_to_cart).setOnClickListener {

        }

    }
}