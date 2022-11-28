package com.aq.lovelocal.model.search

data class Product(
    val brand: Brand,
    val description: String,
    val id: Int,
    val image: String,
    val is_non_vegetarian: Boolean,
    val name: String,
    val requires_prescription: Boolean,
    val variants: List<Variant>
)