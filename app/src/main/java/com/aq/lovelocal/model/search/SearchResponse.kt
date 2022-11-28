package com.aq.lovelocal.model.search

data class SearchResponse(
    val count: Int,
    val `data`: List<Data>,
    val next: String,
    val prev: String,
    val success: Boolean
)