package com.example.lab4_frameworkmobile.data.domain.usecases

import android.util.Log
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import javax.inject.Inject

class InsertUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        try {
            repository.insertUsers(user)
        } catch (e: Exception) {
            Log.e(TAG, "Error inserting user", e)
            throw e
        }
    }
}



