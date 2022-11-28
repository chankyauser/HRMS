package com.aq.lovelocal.model.product

data class ProductResponse(
    val count: Int,
    val `data`: List<Data>,
    val next: String,
    val prev: String,
    val success: Boolean
)