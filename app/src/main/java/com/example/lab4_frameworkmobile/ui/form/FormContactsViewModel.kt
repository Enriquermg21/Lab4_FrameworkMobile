package com.example.lab4_frameworkmobile.ui.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.usecases.InsertUserUseCase
import com.example.lab4_frameworkmobile.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormContactsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val insertUserUseCase: InsertUserUseCase
) : BaseViewModel(savedStateHandle) {
    val user = MutableLiveData<UserEntity>()

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            insertUserUseCase(user)
        }
    }
}
