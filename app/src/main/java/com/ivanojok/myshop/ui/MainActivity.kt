package com.ivanojok.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myshop.R
import com.ivanojok.myshop.data.adapter.ProductAdapter
import com.ivanojok.myshop.data.retofit.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val service = RetrofitInstance().createProductService()
                val products = service.getProducts()

                withContext(Dispatchers.Main) {
                    val productView = findViewById<RecyclerView>(R.id.product_view)
                    val productAdapter = ProductAdapter(this@MainActivity, products)
                    productView.adapter = productAdapter
                    productView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            } catch (t: Throwable) {
                Toast.makeText(this@MainActivity, "No internet Connection", Toast.LENGTH_LONG).show()
            }

        }
    }
}