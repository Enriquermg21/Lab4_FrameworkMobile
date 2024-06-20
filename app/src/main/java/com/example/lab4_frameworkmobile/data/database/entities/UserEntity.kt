package com.example.lab4_frameworkmobile.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lab4_frameworkmobile.data.domain.model.user.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "dateOfBirth") val dateOfBirth: String,
    @ColumnInfo(name = "color") val color: String,
    @ColumnInfo(name = "favoriteCity") val favoriteCity: String,
    @ColumnInfo(name = "favoriteNumber") val favoriteNumber: String,
    @ColumnInfo(name = "currentLocation") val currentLocation: String
)

fun User.toDatabase() = UserEntity(
    name = name,
    dateOfBirth = dateOfBirth,
    color = color,
    favoriteCity = favoriteCity,
    favoriteNumber = favoriteNumber,
    currentLocation = currentLocation
)