package com.example.lab4_frameworkmobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab4_frameworkmobile.data.database.dao.UserDao
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun quoteDao(): UserDao
}