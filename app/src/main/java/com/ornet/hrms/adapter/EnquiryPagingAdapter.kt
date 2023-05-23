package com.ornet.hrms.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ornet.hrms.R
import com.ornet.hrms.model.EnquiryCall

class EnquiryPagingAdapter :
    PagingDataAdapter<EnquiryCall, EnquiryPagingAdapter.QuoteViewHolder>(COMPARATOR) {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val number = itemView.findViewById<TextView>(R.id.number)
        val name = itemView.findViewById<TextView>(R.id.name)
        val call = itemView.findViewById<ImageView>(R.id.call)
        val add = itemView.findViewById<ImageView>(R.id.add)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.number.text = item.mobileNo
            holder.name.text = item.candidateName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_assigned_calls, parent, false)
        return QuoteViewHolder(view)
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<EnquiryCall>() {
            override fun areItemsTheSame(oldItem: EnquiryCall, newItem: EnquiryCall): Boolean {
                return oldItem.enquiryCd == newItem.enquiryCd
            }

            override fun areContentsTheSame(oldItem: EnquiryCall, newItem: EnquiryCall): Boolean {
                return oldItem == newItem
            }
        }
    }
}