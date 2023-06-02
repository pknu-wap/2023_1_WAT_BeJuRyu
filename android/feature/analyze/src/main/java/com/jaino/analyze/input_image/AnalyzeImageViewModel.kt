package com.jaino.analyze.input_image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.analysis.AnalysisRepository
import com.jaino.data.repository.user.LocalUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalyzeImageViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository,
    private val userRepository: LocalUserRepository
): ViewModel(){

    private val _imageSourceState = MutableStateFlow<String>("")

    private val _imageUri = MutableStateFlow<String>("")
    val imageUri : StateFlow<String> get() = _imageUri

    private val userId = MutableStateFlow<Long>(-1L)

    private val _analysisId = MutableStateFlow<Long>(-1L)
    val analysisId : StateFlow<Long> get() = _analysisId

    private val _analysisUiEvent = MutableSharedFlow<UiEvent>()
    val analysisUiEvent : SharedFlow<UiEvent> get() = _analysisUiEvent
    fun getUserId(){
        viewModelScope.launch {
            userId.value = userRepository.getUserId()
        }
    }

    fun setImageUri(imageUri: String){
        _imageUri.value = imageUri
    }
    fun setImageSource(analyzeImage: String){
        _imageSourceState.value = analyzeImage
    }

    fun postAnalysisSource(textSource: String){
        viewModelScope.launch {
            if(userId.value == -1L) {
                return@launch
            }
            analysisRepository.postAnalysisSource(
                userId = userId.value,
                textExpression = textSource,
                facialExpression = _imageSourceState.value
            ).onSuccess {
                _analysisId.value = it.analysisId
            }.onFailure {
                _analysisUiEvent.emit(UiEvent.Failure(it.message))
            }
        }
    }
    sealed class UiEvent{
        data class Failure(val message: String?) : UiEvent()
    }
}