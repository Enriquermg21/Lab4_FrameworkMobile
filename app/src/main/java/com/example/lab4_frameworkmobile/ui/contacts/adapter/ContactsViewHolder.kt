package com.example.lab4_frameworkmobile.ui.contacts.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding

class ContactsViewHolder(private val binding: FragmentContactsItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun onBind(user: UserEntity) {
        val nameString = itemView.context.getString(R.string.user_detail_name)
        val dateString = itemView.context.getString(R.string.user_detail_dateOfBirth)
        val cityString = itemView.context.getString(R.string.user_detail_favorite_city)
        binding.apply {
            itemName.text = "$nameString " + user.name
            itemDateOfBirth.text = "$dateString " + user.dateOfBirth
            itemFavoriteCity.text = "$cityString " + user.favoriteCity
        }
    }
}