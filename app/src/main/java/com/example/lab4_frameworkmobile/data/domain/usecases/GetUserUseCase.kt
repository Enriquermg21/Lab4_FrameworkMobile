package com.example.lab4_frameworkmobile.data.domain.usecases

import android.util.Log
import com.example.lab4_frameworkmobile.data.database.entities.toDatabase
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import javax.inject.Inject

class GetUserUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(): List<User> {
        Log.d("GetUserUseCase", "invoke called")

        val users = repository.getAllUsersFromDatabase()
        Log.d("GetUserUseCase", "Users fetched from database: $users")

        return if (users.isNotEmpty()) {
            Log.d("GetUserUseCase", "Users list is not empty")
            repository.clearUsers()
            Log.d("GetUserUseCase", "Users cleared from repository")

            repository.insertUsersList(users.map { it.toDatabase() })
            Log.d("GetUserUseCase", "Users re-inserted to repository")

            users
        } else {
            Log.d("GetUserUseCase", "Users list is empty")
            repository.getAllUsersFromDatabase()
        }
    }
}
