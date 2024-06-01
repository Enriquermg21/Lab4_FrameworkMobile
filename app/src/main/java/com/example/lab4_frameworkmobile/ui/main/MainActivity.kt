package com.example.pokeapi.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4_frameworkmobile.databinding.ListadoMainBinding
import com.example.lab4_frameworkmobile.ui.main.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ListadoMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListadoMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}