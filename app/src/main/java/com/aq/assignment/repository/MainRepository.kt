package com.aq.assignment.repository

import android.app.Application
import com.aq.assignment.apicall.APIClient
import com.aq.assignment.model.UserData
import com.aq.assignment.room.UserDao
import com.aq.assignment.room.UserDatabase
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIClient
) {
    suspend fun getData() = apiHelper.getData()

}