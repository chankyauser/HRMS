package com.aq.assignment.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.assignment.R
import com.aq.assignment.databinding.ItemUserBinding
import com.aq.assignment.model.UserData
import com.aq.assignment.util.BSDetailsFragment
import com.bumptech.glide.Glide

class ListAdapter(
    var context: Context,
    var userData: ArrayList<UserData>,
    private val listener: ((data: UserData) -> Unit)? = null
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bindData(context, userData[position], position)
    }
    override fun getItemCount(): Int {
        return userData.size
    }
    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(context: Context, user: UserData, position: Int) {
            Glide.with(context)
                .load(user.thumbnailUrl)
                .placeholder(R.drawable.user)
                .into(binding.imgAvatar)
            binding.txtName.text = "${user.title}"
            binding.root.setOnClickListener { listener?.invoke(user) }
        }
    }

}