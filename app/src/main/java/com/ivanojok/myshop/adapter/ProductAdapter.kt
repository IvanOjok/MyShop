package com.ivanojok.myshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myshop.model.ProductResponse
import com.ivanojok.myshop.R
import com.squareup.picasso.Picasso

class ProductAdapter(val context:Context, val list: List<ProductResponse>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.product_image)
        val name = view.findViewById<TextView>(R.id.product_name)
        val rating = view.findViewById<RatingBar>(R.id.ratingBar)
        val price = view.findViewById<TextView>(R.id.product_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.product_layout, parent, false)
        return ProductViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        Picasso.get().load(list[position].image).into(holder.image)
        holder.name.text = list[position].title
        holder.price.text = "$${list[position].price}"
        holder.rating.rating = list[position].rating.rate?.toFloat() ?: 0.0f
    }
}