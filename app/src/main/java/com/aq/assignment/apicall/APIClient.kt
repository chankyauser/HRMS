package com.aq.assignment.apicall

import com.aq.assignment.model.UserData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIClient {
    @GET("photos")
    suspend fun getData(): Response<List<UserData>>

}