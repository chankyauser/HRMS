package com.aq.assignment.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class UserData(
    val `data`: List<Data>,
    val page: Int,
    val per_page: Int,
    val support: Support,
    val total: Int,
    val total_pages: Int
) {
    @Entity(tableName = "users")
    data class Data(
        val avatar: String,
        val email: String,
        val first_name: String,
        @PrimaryKey
        val id: Int,
        val last_name: String
    )

    data class Support(
        val text: String,
        val url: String
    )
}