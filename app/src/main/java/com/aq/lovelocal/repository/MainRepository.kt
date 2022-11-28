package com.aq.lovelocal.repository

import com.aq.lovelocal.apicall.APIClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIClient
) {
    suspend fun searchProduct(searchQuery: String) = apiHelper.searchProduct(searchQuery)
    suspend fun getProductByCate(id: Int) = apiHelper.productByCategory(id.toString())
    suspend fun getCartData() = apiHelper.getCartData()
}