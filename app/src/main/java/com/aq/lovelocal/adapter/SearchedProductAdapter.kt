package com.aq.lovelocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.lovelocal.R
import com.aq.lovelocal.databinding.ItemProductBinding
import com.aq.lovelocal.model.search.Product
import com.bumptech.glide.Glide

class SearchedProductAdapter(
    var context: Context,
    var searchList: ArrayList<Product>
) : RecyclerView.Adapter<SearchedProductAdapter.ViewHolder>() {
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
        viewHolder.bindData(context, searchList[position], position)
    }

    override fun getItemCount(): Int {
        return searchList.size
    }

    inner class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(context: Context, product: Product, position: Int) {

            Glide.with(context)
                .load(product.image)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imgProduct)
            binding.prodNam.text = product.name
            if (product.variants.isNullOrEmpty())
                binding.price.text = "00 RS"
            else
                binding.price.text = "${product.variants[0].price} RS"


        }
    }
}