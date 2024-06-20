package com.example.lab4_frameworkmobile.ui.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.usecases.InsertUserUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class FormContactsViewModel @Inject constructor(val insertUserUseCase: InsertUserUseCase) :
    ViewModel() {

    val user = MutableLiveData<UserEntity>()
    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            insertUserUseCase(user)
        }
    }
}