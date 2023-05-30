package com.jaino.analyze.input_image

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AnalyzeImageViewModel: ViewModel(){

    private val _analyzeImageState = MutableStateFlow<String>("")
    val analyzeImageState : StateFlow<String> get() = _analyzeImageState

    fun setImageUri(analyzeImage: String){
        _analyzeImageState.value = analyzeImage
    }

    fun postAnalyzeInput(analyzeText: String){

    }
}