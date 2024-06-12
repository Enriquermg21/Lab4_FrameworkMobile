package com.example.lab4_frameworkmobile.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.UserListItemBinding

class UserViewHolder(private val binding: UserListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User) {
        binding.textViewName.text = user.name
    }
}
