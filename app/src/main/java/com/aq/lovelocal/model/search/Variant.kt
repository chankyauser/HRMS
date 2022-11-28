package com.aq.lovelocal.model.search

data class Variant(
    val description: String,
    val id: Int,
    val image: String,
    val measurement: Measurement,
    val name: String,
    val price: Double,
    val units: Double
)