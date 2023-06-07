package com.jaino.analysis.image_input

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.common.flow.EventFlow
import com.jaino.common.flow.MutableEventFlow
import com.jaino.common.flow.asEventFlow
import com.jaino.common.model.UiEvent
import com.jaino.data.repository.analysis.AnalysisRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageInputViewModel @Inject constructor(
    private val analysisRepository: AnalysisRepository
): ViewModel(){

    private val _imageSourceState = MutableStateFlow<String>("")

    private val _imageUri = MutableStateFlow<String>("")
    val imageUri : StateFlow<String> get() = _imageUri

    private val _analysisId = MutableStateFlow<Long>(-1L)
    val analysisId : StateFlow<Long> get() = _analysisId

    private val _analysisUiEvent : MutableEventFlow<UiEvent<Unit>> = MutableEventFlow()
    val analysisUiEvent : EventFlow<UiEvent<Unit>> get() = _analysisUiEvent.asEventFlow()

    fun setImageUri(imageUri: String){
        _imageUri.value = imageUri
    }
    fun setImageSource(analyzeImage: String){
        _imageSourceState.value = analyzeImage
    }

    fun postAnalysisSource(date : String, textSource: String){
        viewModelScope.launch {
            analysisRepository.postAnalysisSource(
                date = date,
                textExpression = textSource,
                facialExpression = _imageSourceState.value
            ).onSuccess {
                _analysisId.value = it.analysisId
            }.onFailure {
                _analysisUiEvent.emit(UiEvent.Failure(it))
            }
        }
    }
}