package com.jaino.setting.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.auth.AuthRepository
import com.jaino.data.repository.auth.SocialAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val repository : AuthRepository,
    private val socialAuthRepository: SocialAuthRepository
): ViewModel() {

    private val _accountUiState = MutableStateFlow<UiEvent>(UiEvent.Init)
    val accountUiState : StateFlow<UiEvent> get() = _accountUiState

    fun signOut(){
        viewModelScope.launch {
            repository.clear()
            socialAuthRepository.signOut()
        }
    }

    fun deleteAccount(){
        viewModelScope.launch {
            socialAuthRepository.unlink()
        }
    }

    sealed class UiEvent{
        object Init: UiEvent()
        object Success: UiEvent()
        data class Failure(val message: String?): UiEvent()
    }
}