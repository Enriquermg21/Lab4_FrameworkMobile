package com.example.lab4_frameworkmobile.ui.contacts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.ListAdapter
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.FragmentContactsItemBinding
import com.example.lab4_frameworkmobile.ui.contacts.ContactsFragmentDirections

class ContactsAdapter : ListAdapter<User, ContactsViewHolder>(UserDiffCallback()) {

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
        holder.onBind(getItem(position))
        holder.itemView.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToUserData()
            findNavController(holder.itemView).navigate(action)
        }
    }
}

/*MODIFICAR LOS POPUPS DE LA NAVEGACION
* ARREGLAR LAS VISTAS, HACERLAS MAS BONITAS
* USAR EL HIDEKEYBOARD O USAR BIEN EL SCROLLLAYOUT PARA QUE EL TECLADO NO SE QUEDE ENCIMA DE LOS DATOS*/