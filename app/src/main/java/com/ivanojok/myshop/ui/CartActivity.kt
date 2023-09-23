package com.ivanojok.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myshop.R
import com.ivanojok.myshop.ui.adapter.CartAdapter
import com.ivanojok.myshop.vm.CartViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartActivity : AppCompatActivity() {

    val viewModel: CartViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        lifecycleScope.launch(Dispatchers.Main) {
            val products = viewModel.getCartList(this@CartActivity)
            val cartAdapter = CartAdapter(this@CartActivity, products, viewModel)

            val view = findViewById<RecyclerView>(R.id.cart_recycler)
            view.adapter = cartAdapter
            view.layoutManager = LinearLayoutManager(this@CartActivity)
        }

    }
}