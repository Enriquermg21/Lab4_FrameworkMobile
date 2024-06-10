package com.example.lab4_frameworkmobile.ui.main

import MainViewModel
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab4_frameworkmobile.databinding.ListadoMainBinding
import com.example.lab4_frameworkmobile.ui.adapter.UserAdapter
import com.example.lab4_frameworkmobile.ui.navigation.AddUserActivity
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ListadoMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private val userAdapter = UserAdapter { user ->
        // Aquí puedes manejar el click en un usuario
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListadoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivity(intent)
        }
        // Observa los cambios en la lista de usuarios
        mainViewModel.users.observe(this) { users ->
            userAdapter.submitList(users)
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }
}
