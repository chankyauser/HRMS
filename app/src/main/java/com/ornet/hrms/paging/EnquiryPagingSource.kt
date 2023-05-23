package com.ornet.hrms.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ornet.hrms.apicall.APIClient
import com.ornet.hrms.model.EnquiryCall
import retrofit2.http.Field
import java.lang.Exception
import java.lang.Math.ceil

class EnquiryPagingSource(val apiClient: APIClient, val appName: String, val offset: Int, val itemscount: Int, val operation: String,
                          val Executive_Cd: Int, val UserName: String, val Designation: String, val AccessFlag: String,
                          val DataFilter1: String) : PagingSource<Int, EnquiryCall>() {
    override fun getRefreshKey(state: PagingState<Int, EnquiryCall>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, EnquiryCall> {
        return try {

            val position = params.key ?: 1
            val response = apiClient.enquiryAssignedCallList(appName, position, itemscount, operation, Executive_Cd, UserName, Designation, AccessFlag, DataFilter1)

            return LoadResult.Page(
                data = response.body()!!.enquiryCallList,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == ceil(response.body()!!.totalRecordEC.totalRecord / itemscount.toDouble()).toInt()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}