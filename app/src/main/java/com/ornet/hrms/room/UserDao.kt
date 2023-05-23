package com.ornet.hrms.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ornet.hrms.model.LoginResponse
import com.ornet.hrms.model.UserData

/**
 * Created by AQUIB RASHID SHAIKH on 25-03-2023.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<LoginResponse.UserInformations>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: LoginResponse.UserInformations)

    @Query("DELETE FROM users")
    fun deleteAll()
}
