package com.example.lab4_frameworkmobile.data.repository

import com.example.lab4_frameworkmobile.data.database.dao.UserDao
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getAllUsersFromDatabase(): List<UserEntity> {
        return userDao.getAllUsers()
    }

    suspend fun insertUsers(user: UserEntity) {
        userDao.insertAll(user)
    }

    suspend fun getUserByName(userName: String): UserEntity {
        return userDao.getUserByName(userName)
    }

    suspend fun updateUser(user: UserEntity) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserEntity) {
        userDao.deleteUser(user)
    }
}