package com.ornet.hrms.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.ornet.hrms.apicall.APIClient
import com.ornet.hrms.paging.EnquiryPagingSource
import javax.inject.Inject

class EnquiryRepository @Inject constructor(private val apiClient: APIClient) {
    fun getEnquiry(appName: String, offset: Int, itemscount: Int, operation: String, Executive_Cd: Int, UserName: String, Designation: String,
                   AccessFlag: String, DataFilter1: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = { EnquiryPagingSource(apiClient, appName, offset, itemscount, operation, Executive_Cd, UserName, Designation, AccessFlag, DataFilter1) }
    ).liveData
}