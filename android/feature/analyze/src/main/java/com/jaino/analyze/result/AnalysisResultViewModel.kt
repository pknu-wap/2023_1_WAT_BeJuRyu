package com.jaino.analyze.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.data.repository.user.LocalUserRepository
import com.jaino.model.analysis.SentimentAnalysis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisResultViewModel @Inject constructor(
    private val repository: AnalysisRepository,
    private val userRepository: LocalUserRepository
): ViewModel(){

    private val _analysisResultUiState = MutableStateFlow<SentimentAnalysis>(SentimentAnalysis())
    val analysisResultUiState : StateFlow<SentimentAnalysis> get() = _analysisResultUiState

    private val _analysisResultUiEvent = MutableSharedFlow<UiEvent>()
    val analysisResultUiEvent : SharedFlow<UiEvent> get() = _analysisResultUiEvent

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
                    when (it.result.sentiment) {
                        SAD -> {
                            _sentimentIconState.value = SAD_ICON
                            _sentimentContentState.value = SAD_CONTENT
                            _analysisResultUiState.value = it
                        }
                        HAPPY -> {
                            _sentimentIconState.value = HAPPY_ICON
                            _sentimentContentState.value = HAPPY_CONTENT
                            _analysisResultUiState.value = it
                        }
                        else -> {
                            _analysisResultUiEvent.emit(UiEvent.Failure("조회 결과가 없습니다."))
                        }
                    }
                }.onFailure {
                    _analysisResultUiEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }

    companion object{
        val SAD_ICON = com.jaino.designsystem.R.drawable.sad
        val HAPPY_ICON = com.jaino.designsystem.R.drawable.smile
        const val SAD = "슬픔"
        const val HAPPY = "기쁨"
        const val SAD_CONTENT = "힘들 때는 술 한 잔이 약이될 수 있어요.\n술 한 잔하며, 다 같이 스트레스를 풀어볼까요?"
        const val HAPPY_CONTENT = "오늘 재밌고 행복한 하루를 보내셨네요!\n술 한 잔하며, 분위기를 올려볼까요?"
    }

    sealed class UiEvent{
        data class Failure(val message: String?) : UiEvent()
    }
}