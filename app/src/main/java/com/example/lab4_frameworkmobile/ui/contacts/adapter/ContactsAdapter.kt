package com.example.lab4_frameworkmobile.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding
import com.example.lab4_frameworkmobile.ui.contacts.ContactsFragmentDirections

class ContactsAdapter(
    private val users: List<UserEntity>,
) : ListAdapter<User, ContactsViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        return ContactsViewHolder(
            FragmentContactsItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        val user = getItem(position)
        holder.onBind(user)

        holder.itemView.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToUserData(user.name)
            findNavController(holder.itemView).navigate(action)
        }
    }
}

/*
TODO()
 AÑADIR LATITUD A LOCALICACION
 BOTON VISTA MAPA
 MODIFICAR LOS POPUPS DE LA NAVEGACION
 ARREGLAR LAS VISTAS,
 HACERLAS MAS BONITAS
 USAR EL HIDEKEYBOARD
 COLOCAR BIEN LA INTRODUCCION DE LOS DATOS
 hacer la vista del mapa entero*/