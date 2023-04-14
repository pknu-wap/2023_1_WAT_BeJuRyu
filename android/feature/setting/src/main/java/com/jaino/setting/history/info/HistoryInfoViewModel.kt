package com.jaino.setting.history.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.user.UserRepository
import com.jaino.model.user.AnalysisSentiment
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryInfoViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {

    private val _historyInfoUiState = MutableStateFlow<UiEvent>(UiEvent.Init)
    val historyInfoUiState : StateFlow<UiEvent> get() = _historyInfoUiState

    private val _analyzeSentimentState =
        MutableStateFlow<AnalysisSentiment>(AnalysisSentiment())
    val analysisSentimentState : StateFlow<AnalysisSentiment>
        get() = _analyzeSentimentState

    fun getAnalyzeSentiment(id: Long){
        viewModelScope.launch {
            runCatching{
                repository.getUserAnalysisInfo(id)
            }.onSuccess {
                _analyzeSentimentState.value = it
            }.onFailure {
                _historyInfoUiState.value = UiEvent.Failure("사용자 정보를 불러오는데 실패하였습니다.")
            }
        }
    }

    sealed class UiEvent{
        object Init: UiEvent()
        data class Failure(val message: String?) : UiEvent()
    }
}