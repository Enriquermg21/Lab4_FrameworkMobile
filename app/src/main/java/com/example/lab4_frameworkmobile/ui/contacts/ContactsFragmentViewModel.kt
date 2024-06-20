package com.example.lab4_frameworkmobile.ui.contacts

import androidx.lifecycle.SavedStateHandle
import com.example.lab4_frameworkmobile.data.domain.usecases.GetUserUseCase
import com.example.lab4_frameworkmobile.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactsFragmentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserUseCase: GetUserUseCase
) : BaseViewModel(savedStateHandle) {

    suspend fun getUser() = getUserUseCase()
}