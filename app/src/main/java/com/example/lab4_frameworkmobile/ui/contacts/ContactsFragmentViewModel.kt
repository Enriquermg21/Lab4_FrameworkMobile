package com.example.lab4_frameworkmobile.ui.contacts

import androidx.lifecycle.SavedStateHandle
import com.example.lab4_frameworkmobile.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class ContactsFragmentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel(savedStateHandle) {
    private val _listLoad: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val listLoad: SharedFlow<Boolean> get() = _listLoad
}