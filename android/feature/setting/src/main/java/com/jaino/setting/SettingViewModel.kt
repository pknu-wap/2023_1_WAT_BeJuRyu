package com.jaino.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.setting.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    private val _settingUiEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow<UiEvent<Unit>>()
    val settingUiEvent : EventFlow<UiEvent<Unit>> get() = _settingUiEvent.asEventFlow()

    private val _nicknameState : MutableStateFlow<String> = MutableStateFlow<String>("")
    val nicknameState : StateFlow<String> get() = _nicknameState

    fun getNickname(){
        viewModelScope.launch {
            repository.getProfile()
                .onSuccess { profile ->
                    _nicknameState.value = profile.nickname
                }
                .onFailure {
                    _settingUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}