package com.example.lab4_frameworkmobile.ui.singleton

import com.example.lab4_frameworkmobile.data.domain.model.user.User

object Singleton {
    var userList = mutableListOf<User>()
}