package com.aq.lovelocal.model.cart

data class CartResponse(
    val count: Int,
    val `data`: List<CartData>,
    val success: Boolean
)