package com.example.lab4_frameworkmobile.ui.user

import java.io.Serializable

data class User(
    val name: String,
    val color: String,
    val dateOfBirth: String,
    val favoriteCity: String,
    val favoriteNumber: Int,
    val currentLocation: String
) : Serializable
