package com.aq.lovelocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.lovelocal.R
import com.aq.lovelocal.databinding.ItemProductBinding
import com.aq.lovelocal.model.product.Data
import com.bumptech.glide.Glide

class ProductAdapter (  var context: Context,
var productList: ArrayList<Data>
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
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
        fun bindData(context: Context, product: Data, position: Int) {
            Glide.with(context)
                .load(product.name)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imgProduct)
            binding.prodNam.text = product.name
            binding.price.visibility = View.GONE
            binding.textView2.visibility = View.GONE
        }
    }
}