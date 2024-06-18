package com.example.lab4_frameworkmobile.ui.contacts.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding

class ContactsViewHolder(val binding: FragmentContactsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun onBind(user: User) {
        binding.apply {
            itemName.text = user.name
            itemDateOfBirth.text = user.dateOfBirth
        }
    }
}