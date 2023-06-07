package com.jaino.setting.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
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

    private val _profileUiEvent : MutableEventFlow<UiEvent<Unit>> =  MutableEventFlow<UiEvent<Unit>>()
    val profileUiEvent : EventFlow<UiEvent<Unit>> get() = _profileUiEvent.asEventFlow()

    fun getNickName(){
        viewModelScope.launch {
            repository.getProfile()
                .onSuccess { profile ->
                    _nickNameState.value = profile.nickname
                }
                .onFailure {
                    _profileUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    fun updateNickname(nickname: String){
        viewModelScope.launch {
            _nickNameState.value = nickname
            repository.editNickname(localRepository.getUserId(), _nickNameState.value)
                .onSuccess { profile ->
                    _nickNameState.value = profile.nickname
                    localRepository.setNickname(nickname)
                }
                .onFailure {
                    _profileUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}