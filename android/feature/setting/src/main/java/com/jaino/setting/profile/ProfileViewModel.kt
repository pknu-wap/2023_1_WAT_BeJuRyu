package com.jaino.setting.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.setting.ProfileRepository
import com.jaino.data.repository.user.LocalUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val localRepository: LocalUserRepository,
    private val repository: ProfileRepository
): ViewModel(){

    private val _nickNameState : MutableStateFlow<String> = MutableStateFlow<String>("")
    val nickNameState : StateFlow<String> get() = _nickNameState

    private val _profileUiEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val profileUiEvent : SharedFlow<UiEvent> get() = _profileUiEvent

    fun getNickName(){
        viewModelScope.launch {
            repository.getProfile(localRepository.getUserId())
                .onSuccess { profile ->
                    // _nickNameState.value = profile.nickname
                }
                .onFailure {
                    _profileUiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    fun updateNickname(nickname: String){
        viewModelScope.launch {
            _nickNameState.value = nickname
            repository.editNickname(localRepository.getUserId(), _nickNameState.value)
                .onSuccess { profile ->
                    _nickNameState.value = profile.nickname
                }
                .onFailure {
                    _profileUiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message: String?): UiEvent()
    }
}