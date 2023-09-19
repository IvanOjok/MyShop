package com.ivanojok.myshop.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        CoroutineScope(Dispatchers.IO).launch {

                val products =  viewModel.getProducts().collect {
                    if (it!=null) {
                        withContext(Dispatchers.Main) {
                            val productView =
                                findViewById<androidx.recyclerview.widget.RecyclerView>(
                                    com.ivanojok.myshop.R.id.product_view
                                )
                            val productAdapter = com.ivanojok.myshop.ui.adapter.ProductAdapter(
                                this@MainActivity,
                                it
                            )
                            productView.adapter = productAdapter
                            productView.layoutManager =
                                androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
                        }
                    }
                }




        }
    }
}