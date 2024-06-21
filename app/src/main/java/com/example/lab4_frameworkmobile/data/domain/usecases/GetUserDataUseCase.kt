package com.example.lab4_frameworkmobile.data.domain.usecases

import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(userName: String): UserEntity {
        return repository.getUserByName(userName)
    }
}