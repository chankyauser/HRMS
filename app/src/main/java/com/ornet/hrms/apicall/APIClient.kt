package com.ornet.hrms.apicall

import com.ornet.hrms.model.EnquiryAssignedCallListRes
import com.ornet.hrms.model.LoginResponse
import com.ornet.hrms.model.UserData
import com.ornet.hrms.ui.EnquiryAssignedList
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIClient {
    @FormUrlEncoded
    @POST("login.php")
    suspend fun doLogin(
        @Field("mobileNumber") mobile_no: String,
        @Field("password") otp: String,
        @Field("appName") appName: String = "HRMS"
    ): Response<LoginResponse>


    @FormUrlEncoded
    @POST("EnquiryMaster.php")
    suspend fun enquiryAssignedCallList(
        @Field("appName") appName: String,
        @Field("offset") offset: Int,
        @Field("itemscount") itemscount: Int,
        @Field("operation") operation: String,
        @Field("Executive_Cd") Executive_Cd: Int,
        @Field("UserName") UserName: String,
        @Field("Designation") Designation: String,
        @Field("AccessFlag") AccessFlag: String,
        @Field("DataFilter1") DataFilter1: String
    ): Response<EnquiryAssignedCallListRes>

}