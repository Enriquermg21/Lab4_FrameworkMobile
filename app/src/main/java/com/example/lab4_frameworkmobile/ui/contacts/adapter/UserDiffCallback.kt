package com.example.lab4_frameworkmobile.ui.contacts.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.lab4_frameworkmobile.data.domain.model.user.User

class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}