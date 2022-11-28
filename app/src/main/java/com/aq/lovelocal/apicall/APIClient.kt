package com.aq.lovelocal.apicall

import com.aq.lovelocal.model.cart.CartData
import com.aq.lovelocal.model.cart.CartResponse
import com.aq.lovelocal.model.product.ProductResponse
import com.aq.lovelocal.model.search.GeneralResponse
import com.aq.lovelocal.model.search.Product
import com.aq.lovelocal.model.search.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIClient {
    @GET("search/product")
    suspend fun searchProduct(
        @Query("searchText") searchText: String,
        @Query("latitude") latitude: String = "27",
        @Query("longitude") longitude: String = "80",
        @Query("page") page: String = "1",
        @Query("records") records: String = "10"
    ): Response<SearchResponse>

    @GET("search/category/{category_ID}")
    suspend fun productByCategory(
        @Path("category_ID") searchText: String,
        @Query("latitude") latitude: String = "27",
        @Query("longitude") longitude: String = "80",
        @Query("page") page: String = "1",
        @Query("records") records: String = "10"
    ): Response<ProductResponse>

    @GET("orders/all")
    suspend fun getCartData(
    ): Response<CartResponse>
}