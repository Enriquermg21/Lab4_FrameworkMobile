package com.example.lab4_frameworkmobile.ui.userdata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.usecases.GetUserDataUseCase
import com.example.lab4_frameworkmobile.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDataViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel(savedStateHandle) {
    val userFull = MutableLiveData<UserEntity>()
    fun loadUserByName(userName: String) {
        viewModelScope.launch {
            try {
                val result = getUserDataUseCase(userName)
                userFull.value = result

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateUser() {
        TODO()
    }
}