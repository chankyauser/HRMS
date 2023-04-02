package com.aq.assignment.apicall

import com.aq.assignment.model.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("users")
    suspend fun getData(
        @Query("page") page: String = "1",
        @Query("per_page") records: String = "10"
    ): Response<UserData>

}