package com.aq.lovelocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.lovelocal.R
import com.aq.lovelocal.databinding.ItemCategoryBinding
import com.aq.lovelocal.model.CategoryModel
import com.aq.lovelocal.util.onItemClick
import com.bumptech.glide.Glide

class CategoryAdapter(var context: Context,
                      var categoryList: ArrayList<CategoryModel>,
                      var onItemClick: onItemClick
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bindData(context, categoryList[position], position,onItemClick)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(context: Context, category: CategoryModel, position: Int,onItemClick: onItemClick) {
            Glide.with(context)
                .load(category.image)
                .placeholder(R.drawable.ic_placeholder)
                .into(binding.imgCategory)
            binding.txtCatName.text = category.name
            binding.root.setOnClickListener{onItemClick.onCLick(position)}
        }
    }
}