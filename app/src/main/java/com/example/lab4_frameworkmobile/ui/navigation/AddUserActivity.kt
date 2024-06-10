package com.example.lab4_frameworkmobile.ui.navigation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lab4_frameworkmobile.databinding.ActivityAddUserBinding
import com.example.lab4_frameworkmobile.ui.user.User

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val color = binding.editTextColor.text.toString()
            val birthDate = binding.editTextBirthday.text.toString()
            val city = binding.editTextCity.text.toString()
            val number = binding.editTextFavoriteNumber.text.toString().toIntOrNull() ?: 0
            val location = "Actual Location" // Aquí se obtiene la localización real

            val user = User(name, color, birthDate, city, number, location)
            val resultIntent = Intent()
            resultIntent.putExtra("user", user)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
