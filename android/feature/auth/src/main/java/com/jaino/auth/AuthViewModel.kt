package com.jaino.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authUiState : MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val authUiState : SharedFlow<UiEvent> get() = _authUiState
    fun executeServiceSignIn(token: String){
        viewModelScope.launch {
            repository.signInService(token).onSuccess {
                _authUiState.emit(UiEvent.Success)
            }.onFailure {
                Timber.e(it)
                _authUiState.emit(UiEvent.Failure(it.message))
            }
        }
    }

    sealed class UiEvent{
        object Success : UiEvent()
        data class Failure(val message: String?) : UiEvent()
    }
}