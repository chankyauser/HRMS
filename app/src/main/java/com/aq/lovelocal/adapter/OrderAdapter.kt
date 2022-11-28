package com.aq.lovelocal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aq.lovelocal.databinding.ItemOrderBinding
import com.aq.lovelocal.model.cart.CartData
import com.aq.lovelocal.model.cart.Product

class OrderAdapter (var context: Context,
                    var orderList: ArrayList<CartData>
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(
        viewHolder: ViewHolder,
        position: Int
    ) {
        viewHolder.bindData(context, orderList[position], position)
    }
    override fun getItemCount(): Int {
        return orderList.size
    }
    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(context: Context, order: CartData, position: Int) {
          binding.txtOrderNo.text = "Order: ${order.order_id}"
          binding.txtProdCount.text = "Product count: ${order.product_count}"
            binding.rvProduct.adapter = OrderProductAdapter(context,
                order.products as ArrayList<Product>
            )
        }
    }
}