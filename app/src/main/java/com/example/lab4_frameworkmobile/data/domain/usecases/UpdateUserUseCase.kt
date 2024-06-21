package com.example.lab4_frameworkmobile.data.domain.usecases

import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.repository.UserRepository
import javax.inject.Inject

class UpdateUserUseCase @Inject constructor(private val repository: UserRepository) {
    suspend operator fun invoke(user: UserEntity) {
        repository.updateUser(user)
    }
}