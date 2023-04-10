package com.jaino.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _authUiState = MutableStateFlow<UiState>(UiState.Init)
    val authUiState : StateFlow<UiState> get() = _authUiState
    fun executeServiceSignIn(token: String){
        viewModelScope.launch {
            repository.signInService(token).onSuccess {
                _authUiState.value = UiState.Success
            }.onFailure {
                _authUiState.value = UiState.Failure(it.message)
            }
        }
    }

    sealed class UiState{
        object Init : UiState()
        object Success : UiState()
        data class Failure(val message: String?) : UiState()
    }
}