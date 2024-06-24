package com.example.lab4_frameworkmobile.ui.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding

class ContactsViewHolder(private val binding: FragmentContactsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(user: UserEntity) {
        binding.apply {
            itemName.text = user.name
        }
    }
}