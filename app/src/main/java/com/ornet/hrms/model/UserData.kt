package com.ornet.hrms.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class UserData(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
):Serializable