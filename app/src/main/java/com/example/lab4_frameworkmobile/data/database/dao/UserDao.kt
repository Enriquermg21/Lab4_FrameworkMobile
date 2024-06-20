package com.example.lab4_frameworkmobile.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAllUsers(): List<UserEntity>

    @Query("DELETE FROM user_table")
    fun deleteAllUsers()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllList(users: List<UserEntity>)

}