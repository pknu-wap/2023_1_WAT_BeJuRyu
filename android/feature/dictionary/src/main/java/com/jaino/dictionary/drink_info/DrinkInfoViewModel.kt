package com.jaino.dictionary.drink_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaino.data.repository.dictionary.DrinksRepository
import com.jaino.model.dictionary.DrinkInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrinkInfoViewModel @Inject constructor(
    private val repository: DrinksRepository
): ViewModel() {

    private val _drinkInfoUiState : MutableStateFlow<DrinkInfo> = MutableStateFlow(DrinkInfo())
    val drinkInfoUiState : StateFlow<DrinkInfo> get() = _drinkInfoUiState

    private val _drinkInfoEvent : MutableSharedFlow<UiEvent> = MutableSharedFlow()
    val drinkInfoEvent : SharedFlow<UiEvent> get() = _drinkInfoEvent
    
    fun getDrinkData(id: Long){
        viewModelScope.launch { 
            repository.getDrinkDataById(id)
                .onSuccess { drinkData ->
                    _drinkInfoUiState.value = drinkData
                }
                .onFailure {
                    _drinkInfoEvent.emit(UiEvent.Failure(it.message))
                }
        }
    }
    
    sealed class UiEvent{
        data class Failure(val message : String?) : UiEvent()
    }
}