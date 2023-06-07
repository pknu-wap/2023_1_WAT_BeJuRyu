package com.jaino.setting.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.common.model.UiState
import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.model.analysis.AnalysisHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository
    ) : ViewModel(){

    private val _historyUiEvent = MutableEventFlow<UiEvent<Unit>>()
    val historyUiEvent : EventFlow<UiEvent<Unit>> get() = _historyUiEvent.asEventFlow()

    private val _historyListState = MutableStateFlow<UiState<List<AnalysisHistory>>>(UiState.Init)
    val historyListState : StateFlow<UiState<List<AnalysisHistory>>> get() = _historyListState

    fun getAnalyzeList(){
        viewModelScope.launch {
            analysisRepository.getAnalysisList()
                .onSuccess {
                    _historyListState.value = UiState.Success(it)
                }
                .onFailure {
                    _historyUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }
}