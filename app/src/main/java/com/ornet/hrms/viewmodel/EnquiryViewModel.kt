package com.ornet.hrms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.ornet.hrms.repository.EnquiryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EnquiryViewModel @Inject constructor(private val repository: EnquiryRepository) : ViewModel() {

    /*, private val appName: String, private val offset: Int, private val itemscount: Int, private val operation: String,
    private val Executive_Cd: Int, private val UserName: String, private val Designation: String, private val AccessFlag: String,
    private val DataFilter1: String*/

    val list = repository.getEnquiry("HRMS", 1, 10, "assigned_EC", 1092, "BHUSHAN_C2", "Software Developer", "NO", "BOTH").cachedIn(viewModelScope)
    //appName, offset, itemscount, operation, Executive_Cd, UserName, Designation, AccessFlag, DataFilter1
}