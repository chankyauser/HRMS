package com.ornet.hrms.model

data class EnquiryAssignedCallListRes(
    val enquiryCallList: List<EnquiryCall>,
    val error: Boolean,
    val message: String,
    val totalRecordEC: TotalRecordEC
)