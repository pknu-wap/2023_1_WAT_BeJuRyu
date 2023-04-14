package com.jaino.setting.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.user.UserRepository
import com.jaino.model.user.Analysis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel(){
    
    private val _historyUiState = MutableStateFlow<UiEvent>(UiEvent.Init)
    val historyUiState : StateFlow<UiEvent> get() = _historyUiState
    
    init{
        getUserAnalyzeList()
    }

    private fun getUserAnalyzeList(){
        viewModelScope.launch {
            runCatching {
                repository.getUserAnalyzeList()
            }.onSuccess {
                if(it.isEmpty()){
                    _historyUiState.value = UiEvent.Failure("기록이 없습니다.")
                }
                else {
                    _historyUiState.value = UiEvent.Success(it)
                }
            }.onFailure {
                _historyUiState.value = UiEvent.Failure("사용자 정보를 불러오는데 실패하였습니다.")
            }
        }
    }

    sealed class UiEvent{
        object Init: UiEvent()
        data class Success(val data: List<Analysis>) : UiEvent()
        data class Failure(val message: String?) : UiEvent()
    }
}