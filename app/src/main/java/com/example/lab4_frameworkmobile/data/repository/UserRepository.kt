package com.example.lab4_frameworkmobile.data.repository

import com.example.lab4_frameworkmobile.data.database.dao.UserDao
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.data.domain.model.user.toDomain
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    suspend fun getAllUsersFromDatabase(): List<User> {
        val response: List<UserEntity> = userDao.getAllUsers()
        return response.map { it.toDomain() }
    }

    suspend fun insertUsersList(user: List<UserEntity>) {
        userDao.insertAllList(user)
    }

    suspend fun insertUsers(user: UserEntity) {
        userDao.insertAll(user)
    }

    suspend fun clearUsers() {
        userDao.deleteAllUsers()
    }
}