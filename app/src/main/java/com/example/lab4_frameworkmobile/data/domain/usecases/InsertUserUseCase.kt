package com.example.lab4_frameworkmobile.data.domain.usecases

import android.util.Log
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        Log.e(TAG, "Error inserting user: $user")
        try {
            Log.d(TAG, "Inserting user: $user")
            repository.insertUsers(user)
            Log.d(TAG, "User inserted successfully")
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting user: $user")
            Log.e(TAG, "Exception message: ${e.message}")
            throw e // Re-throw the exception to be caught by the calling method if needed
        }
    }
}



