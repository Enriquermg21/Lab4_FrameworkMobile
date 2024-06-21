package com.example.lab4_frameworkmobile.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllList(users: List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table WHERE name = :userName")
    suspend fun getUserByName(userName: String): UserEntity
}
