package com.aq.assignment.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aq.assignment.model.UserData
import dagger.Binds
import dagger.Provides

/**
 * Created by AQUIB RASHID SHAIKH on 25-03-2023.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<UserData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<UserData>)

    @Query("DELETE FROM users")
    fun deleteAll()
}
