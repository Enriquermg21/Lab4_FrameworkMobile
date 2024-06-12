package com.example.lab4_frameworkmobile.ui.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab4_frameworkmobile.data.domain.model.user.User
import com.example.lab4_frameworkmobile.databinding.ActivityAddUserBinding

class AddUserViewModel : ViewModel() {

    private val _userList = MutableLiveData<MutableList<User>>()
    val userList: LiveData<MutableList<User>> get() = _userList

    init {
        _userList.value = mutableListOf()
    }

    fun onAddUserButtonClick(binding: ActivityAddUserBinding): User? {
        val name = binding.editTextName.text.toString()
        val color = binding.editTextColor.text.toString()
        val birthDate = binding.editTextBirthday.text.toString()
        val city = binding.editTextCity.text.toString()
        val number = binding.editTextFavoriteNumber.text.toString().toIntOrNull() ?: 0
        val location = "Actual Location" // Aquí se obtiene la localización real

        return if (name.isNotEmpty() && color.isNotEmpty() && birthDate.isNotEmpty() && city.isNotEmpty() && location.isNotEmpty()) {
            val user = User(name, color, birthDate, city, number, location)
            _userList.value?.add(user)
            _userList.value = _userList.value
            Log.d("AddUserViewModel", "User: $user")
            user
        } else {
            Log.d("AddUserViewModel", "Invalid user data. All fields must be filled.")
            null
        }
    }
}
