package com.example.lab4_frameworkmobile.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding
import com.example.lab4_frameworkmobile.ui.contacts.ContactsFragmentDirections

class ContactsAdapter : ListAdapter<UserEntity, ContactsViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val binding = FragmentContactsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ContactsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val user = getItem(position)
        holder.onBind(user)

        holder.itemView.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToUserData(user.name)
            findNavController(holder.itemView).navigate(action)
        }
    }

    fun getUserAtPosition(position: Int): UserEntity {
        return getItem(position)
    }
}


/*
TODO()
 AÑADIR LATITUD A LOCALICACION
 BOTON VISTA MAPA
 MODIFICAR LOS POPUPS DE LA NAVEGACION
 ARREGLAR LAS VISTAS,
 HACERLAS MAS BONITAS
 USAR EL HIDE KEYBOARD
 COLOCAR BIEN LA INTRODUCCION DE LOS DATOS
 hacer la vista del mapa entero*/