package com.aq.lovelocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.aq.lovelocal.R
import com.aq.lovelocal.databinding.ItemProductBinding
import com.aq.lovelocal.model.cart.Product
import com.aq.lovelocal.model.product.Data
import com.bumptech.glide.Glide

class OrderProductAdapter (var context: Context,
                           var productList: ArrayList<Product>
) : RecyclerView.Adapter<OrderProductAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bindData(context, productList[position], position)
    }
    override fun getItemCount(): Int {
        return productList.size
    }
    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(context: Context, product: Product, position: Int) {
            Glide.with(context)
                .load(product.name)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imgProduct)

            binding.prodNam.text = "${product.name} ${product.quantity} ${product.measurement}"
            binding.price.visibility = View.GONE
            binding.textView2.visibility = View.GONE
            binding.add.visibility = View.GONE
        }
    }
}