package com.example.lab4_frameworkmobile.data.domain.model.user

import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

data class User(
    val name: String,
    val dateOfBirth: String,
    val color: String,
    val favoriteCity: String,
    val favoriteNumber: String,
    val currentLocation: String
)

fun UserEntity.toDomain() =
    User(name, dateOfBirth, color, favoriteCity, favoriteNumber, currentLocation)