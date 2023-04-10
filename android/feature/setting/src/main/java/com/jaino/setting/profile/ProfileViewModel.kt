package com.jaino.setting.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.auth.AuthRepository
import com.jaino.data.repository.user.LocalUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository : LocalUserRepository
): ViewModel(){

    private val _nickNameState = MutableStateFlow<String>("")
    val nickNameState : StateFlow<String> get() = _nickNameState

    init{
        getNickName()
    }

    private fun getNickName(){
        viewModelScope.launch {
            _nickNameState.value = repository.getNickName()
        }
    }
}