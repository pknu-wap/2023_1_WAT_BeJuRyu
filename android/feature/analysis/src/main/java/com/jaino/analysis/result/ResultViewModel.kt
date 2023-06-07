package com.jaino.analysis.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.constant.*
import com.jaino.common.extensions.toSentimentKor
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.model.analysis.SentimentAnalysis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val repository: AnalysisRepository,
    private val userRepository: LocalUserRepository
): ViewModel(){

    private val _analysisResultUiEvent = MutableEventFlow<UiEvent<Unit>>()
    val analysisResultUiEvent : EventFlow<UiEvent<Unit>> get() = _analysisResultUiEvent.asEventFlow()

    private val _analysisResultUiState = MutableStateFlow<SentimentAnalysis>(SentimentAnalysis())
    val analysisResultUiState : StateFlow<SentimentAnalysis> get() = _analysisResultUiState

    private val _nicknameState = MutableStateFlow<String>("")
    val nicknameState : StateFlow<String> get() = _nicknameState

    // 감정 icon state
    private val _sentimentIconState = MutableStateFlow<Int>(0)
    val sentimentIconState : StateFlow<Int> get() = _sentimentIconState

    // 감정 content state
    private val _sentimentContentState = MutableStateFlow<String>("")
    val sentimentContentState : StateFlow<String> get() = _sentimentContentState

    fun getNickname(){
        viewModelScope.launch {
            _nicknameState.value = userRepository.getNickName()
        }
    }

    fun getAnalysisResult(analysisId: Long){
        viewModelScope.launch {
            repository.getSentimentAnalysis(analysisId = analysisId)
                .onSuccess {
                    val result = it.copy(sentiment = it.sentiment.toSentimentKor())
                    when (result.sentiment) {
                        SAD -> {
                            _sentimentIconState.value = SAD_ICON // icon
                            _sentimentContentState.value = SAD_CONTENT // content
                            _analysisResultUiState.value = result
                        }
                        HAPPY -> {
                            _sentimentIconState.value = HAPPY_ICON
                            _sentimentContentState.value = HAPPY_CONTENT
                            _analysisResultUiState.value = result
                        }
                        MEDIAN -> {
                            _sentimentIconState.value = MEDIAN_ICON
                            _sentimentContentState.value = MEDIAN_CONTENT
                            _analysisResultUiState.value = result
                        }
                    }
                }.onFailure {
                    _analysisResultUiEvent.emit(UiEvent.Failure(it))
                }
        }
    }

    companion object{
        val SAD_ICON = com.jaino.designsystem.R.drawable.sad
        val HAPPY_ICON = com.jaino.designsystem.R.drawable.smile
        val MEDIAN_ICON = com.jaino.designsystem.R.drawable.neutral
    }
}