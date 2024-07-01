package com.example.lab4_frameworkmobile.ui.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity

class UserDiffCallback : DiffUtil.ItemCallback<UserEntity>() {
    override fun areItemsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UserEntity, newItem: UserEntity): Boolean {
        return oldItem == newItem
    }
}