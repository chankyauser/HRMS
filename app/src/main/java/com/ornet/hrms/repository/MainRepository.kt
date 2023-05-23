package com.ornet.hrms.repository

import com.ornet.hrms.apicall.APIClient
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: APIClient
) {
    suspend fun doLogin(mobileNo:String, pass:String) = apiHelper.doLogin(mobileNo,pass)

}