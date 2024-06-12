package com.example.lab4_frameworkmobile.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.UserListItemBinding

class UserAdapter(private val onClick: (User) -> Unit) : RecyclerView.Adapter<UserViewHolder>() {

    private var userList: List<User> = listOf()

    fun submitList(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user)
        holder.itemView.setOnClickListener {
            onClick(user)
        }
    }

    override fun getItemCount(): Int = userList.size
}
