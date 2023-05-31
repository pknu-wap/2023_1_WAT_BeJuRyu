package com.jaino.setting.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.model.analysis.AnalysisHistory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository
    ) : ViewModel(){

    private val _historyUiEvent = MutableSharedFlow<UiEvent>()
    val historyUiEvent : SharedFlow<UiEvent> get() = _historyUiEvent

    private val _historyListState = MutableStateFlow<List<AnalysisHistory>>(emptyList())
    val historyListState : StateFlow<List<AnalysisHistory>> get() = _historyListState

    fun getAnalyzeList(){
        viewModelScope.launch {
            analysisRepository.getAnalysisList()
                .onSuccess {
                    _historyListState.value = it
                }
                .onFailure {
                    _historyUiEvent.emit(UiEvent.Failure("사용자 정보를 불러오는데 실패하였습니다."))
                }
        }
    }

    sealed class UiEvent{
        data class Failure(val message: String?) : UiEvent()
    }
}