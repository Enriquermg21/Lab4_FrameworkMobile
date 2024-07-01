package com.example.lab4_frameworkmobile.ui.base

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab4_frameworkmobile.data.domain.model.error.ErrorModel
import com.example.lab4_frameworkmobile.ui.extensions.TAG
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(val savedStateHandle: SavedStateHandle? = null) : ViewModel() {


    protected val loadingMutableSharedFlow = MutableSharedFlow<Boolean>(replay = 0)
    val loadingFlow: SharedFlow<Boolean> = loadingMutableSharedFlow

    protected val errorMutableSharedFlow = MutableSharedFlow<ErrorModel>(replay = 0)
    val errorFlow: SharedFlow<ErrorModel> = errorMutableSharedFlow

    protected val unreadNotifyState = MutableSharedFlow<Int>()
    val unreadNotify: SharedFlow<Int> = unreadNotifyState


    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "l> onCleared")
    }

    fun cancelLoading() {
        viewModelScope.launch {
            loadingMutableSharedFlow.emit(false)
        }
    }
}