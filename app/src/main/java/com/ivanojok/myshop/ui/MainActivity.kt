package com.ivanojok.myshop.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myshop.R
import com.ivanojok.myshop.ui.adapter.ProductAdapter
import com.ivanojok.myshop.data.retofit.RetrofitInstance
import com.ivanojok.myshop.vm.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageView>(R.id.my_cart).setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val items = viewModel.getRemoteItems()

                withContext(Dispatchers.Main) {
                    val productView = findViewById<RecyclerView>(R.id.product_view)
                    val productAdapter = ProductAdapter(this@MainActivity, items!!)
                    productView.adapter = productAdapter
                    productView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            } catch (t: Throwable) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "No internet Connection", Toast.LENGTH_LONG)
                        .show()
                }
            }

        }
    }
}