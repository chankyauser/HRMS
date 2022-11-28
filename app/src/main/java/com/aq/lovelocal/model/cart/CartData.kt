package com.aq.lovelocal.model.cart

data class CartData(
    val order_id: Int,
    val product_count: Int,
    val products: List<Product>
)