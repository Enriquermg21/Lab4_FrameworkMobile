package com.example.lab4_frameworkmobile.ui.main

import MainViewModel
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.lab4_frameworkmobile.R
import com.example.lab4_frameworkmobile.databinding.ListadoMainBinding
import com.example.lab4_frameworkmobile.ui.adapter.UserAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ListadoMainBinding
    private lateinit var navController: NavController
    private val mainViewModel: MainViewModel by viewModels()
    private val userAdapter = UserAdapter { user ->
        // Aquí puedes manejar el click en un usuario

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configNavigation()
        binding = ListadoMainBinding.inflate(layoutInflater)

    }

    private fun observeViewModel() {
        lifecycleScope.launch {
        }
    }

    private fun configNavigation() {
        // Encuentra el fragmento por ID
        val fragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
        Log.d(
            "MainActivity",
            "Fragment encontrado: ${supportFragmentManager.findFragmentById(R.id.nav_host_fragment)}"
        )

        // Intenta convertir el fragmento a NavHostFragment
        val navHostFragment = fragment as? NavHostFragment
        if (navHostFragment != null) {
            // Si la conversión es exitosa, inicializa el NavController
            navController = navHostFragment.navController
            Log.d("MainActivity", "NavController inicializado: $navController")
        } else {
            // Si la conversión falla, loguea un mensaje de error
            Log.e(
                "MainActivity",
                "NavHostFragment no encontrado o no se puede convertir a NavHostFragment"
            )
        }
    }

}
