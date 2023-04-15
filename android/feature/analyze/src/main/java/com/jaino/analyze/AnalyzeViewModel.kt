package com.jaino.analyze

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class AnalyzeViewModel : ViewModel() {

    val textExpressionState = MutableStateFlow<String>("")

}