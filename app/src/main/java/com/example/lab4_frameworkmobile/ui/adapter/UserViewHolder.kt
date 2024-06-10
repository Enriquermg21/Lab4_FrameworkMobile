package com.example.lab4_frameworkmobile.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.databinding.UserListItemBinding
import com.example.lab4_frameworkmobile.ui.user.User

class UserViewHolder(private val binding: UserListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.textViewName.text = user.name
    }
}
