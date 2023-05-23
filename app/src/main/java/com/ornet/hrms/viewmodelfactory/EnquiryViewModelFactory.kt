package com.ornet.hrms.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ornet.hrms.repository.EnquiryRepository
import com.ornet.hrms.viewmodel.EnquiryViewModel

class EnquiryViewModelFactory/*(private val repository: EnquiryRepository, val appName: String, val offset: Int, val itemscount: Int, val operation: String,
                              val Executive_Cd: Int, val UserName: String, val Designation: String, val AccessFlag: String,
                              val DataFilter1: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnquiryViewModel::class.java)) {
            //return EnquiryViewModel(repository, appName, offset, itemscount, operation, Executive_Cd, UserName, Designation, AccessFlag, DataFilter1) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}*/{}