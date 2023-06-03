package com.jaino.setting

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
class SettingViewModel @Inject constructor(
    private val localRepository: LocalUserRepository,
    private val repository: ProfileRepository
): ViewModel() {

    private val _nicknameState : MutableStateFlow<String> = MutableStateFlow<String>("")
    val nicknameState : StateFlow<String> get() = _nicknameState

    private val _settingUiEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow<UiEvent>()
    val settingUiEvent : SharedFlow<UiEvent> get() = _settingUiEvent

    fun getNickname(){
        viewModelScope.launch {
            val userId = localRepository.getUserId()
            repository.getProfile(userId)
                .onSuccess { profile ->
                    _nicknameState.value = profile.nickname
                }
                .onFailure {
                    _settingUiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message: String?): UiEvent()
    }
}