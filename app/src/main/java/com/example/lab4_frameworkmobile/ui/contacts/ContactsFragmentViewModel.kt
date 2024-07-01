package com.example.lab4_frameworkmobile.ui.contacts

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.lab4_frameworkmobile.data.database.entities.UserEntity
import com.example.lab4_frameworkmobile.data.domain.usecases.DeleteUserUseCase
import com.example.lab4_frameworkmobile.data.domain.usecases.GetUserUseCase
import com.example.lab4_frameworkmobile.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsFragmentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase
) : BaseViewModel(savedStateHandle) {
    suspend fun getUser() = getUserUseCase()

    suspend fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            deleteUserUseCase.deleteUser(user)
        }
    }
}