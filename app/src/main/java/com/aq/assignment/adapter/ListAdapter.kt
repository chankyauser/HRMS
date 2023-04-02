package com.aq.assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.assignment.R
import com.aq.assignment.databinding.ItemUserBinding
import com.aq.assignment.model.UserData
import com.bumptech.glide.Glide

class ListAdapter(
    var context: Context,
    var userData: ArrayList<UserData.Data>
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
        fun bindData(context: Context, user: UserData.Data, position: Int) {
            Glide.with(context)
                .load(user.avatar)
                .placeholder(R.drawable.user)
                .into(binding.imgAvatar)
            binding.txtName.text = "${user.first_name} ${user.last_name}"
            binding.txtSubtext.text = user.email
        }
    }
}