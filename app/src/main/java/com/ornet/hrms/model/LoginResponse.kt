package com.ornet.hrms.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class LoginResponse(
    val UserInformation: UserInformations,
    val error: Boolean,
    val message: String
) {
    @Entity(tableName = "users")
    data class UserInformations(
        val Age: Int,
        val AppName: String,
        val Designation: String,
        val ExecutiveName: String,
        val Executive_Cd: Int,
        val Gender: String,
        val Mobile: String,
        val UserName: String,
        @PrimaryKey
        val User_Id: Int
    )
}