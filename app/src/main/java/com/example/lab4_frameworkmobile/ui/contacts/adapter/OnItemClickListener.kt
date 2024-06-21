package com.example.lab4_frameworkmobile.ui.contacts.adapter

import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

interface OnItemClickListener {
    fun onItemClick(user: UserEntity)
}