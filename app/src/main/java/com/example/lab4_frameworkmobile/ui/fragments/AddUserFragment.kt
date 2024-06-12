package com.example.lab4_frameworkmobile.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.lab4_frameworkmobile.databinding.ActivityAddUserBinding

class AddUserFragment : Fragment() {

    private lateinit var binding: ActivityAddUserBinding
    private val adduserviewmodel: AddUserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)

        binding.buttonSave.setOnClickListener {
            val user = adduserviewmodel.onAddUserButtonClick(binding)
            adduserviewmodel.userList.observe(this, { userList ->
                // Aquí puedes actualizar la UI si es necesario
                // Por ejemplo, puedes mostrar la lista en un RecyclerView
            })
            Log.d("AddUserActivity", "User: ----rawer$user")
        }

    }
}
