package com.ivanojok.myshop.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ivanojok.myshop.R
import com.ivanojok.myshop.data.model.ProductResponse
import com.ivanojok.myshop.data.room.CartModel
import com.ivanojok.myshop.ui.DetailsActivity
import com.ivanojok.myshop.vm.CartViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartAdapter(val context: Context, val list: List<CartModel>, val vm:CartViewModel): RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val name = view.findViewById<TextView>(R.id.product_title)
        val price = view.findViewById<TextView>(R.id.price)
        val addIcon = view.findViewById<ImageView>(R.id.add_icon)
        val removeIcon = view.findViewById<ImageView>(R.id.remove_icon)
        val quantity = view.findViewById<TextView>(R.id.quantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false)
        return CartViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Picasso.get().load(list[position].product.image).into(holder.image)
        holder.name.text = list[position].product.title
        holder.price.text = ((list[position].product.price)?.times(list[position].quantity)).toString()
        holder.quantity.text = list[position].quantity.toString()

        holder.addIcon.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val q = vm.addQuantity(context, list[position].id!!)
                holder.quantity.text = q.toString()
                this@CartAdapter.notifyDataSetChanged()
            }
        }

        holder.removeIcon.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val q = vm.removeQuantity(context, list[position].id!!)
                holder.quantity.text = q.toString()
                //notifyItemChanged(position)
                this@CartAdapter.notifyDataSetChanged()
            }
        }

    }
}